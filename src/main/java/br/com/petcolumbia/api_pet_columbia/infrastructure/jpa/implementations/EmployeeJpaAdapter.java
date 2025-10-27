package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.EmployeeEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.EmployeeEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.EmployeeJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeJpaAdapter implements EmployeeGateway {

    private final EmployeeJpaRepository employeeRepository;

    public EmployeeJpaAdapter(EmployeeJpaRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findEmployeeById(Integer employeeId) {
        EmployeeEntity entity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        // Map without appointments to avoid circular references
        MappingContext context = new MappingContext(new MappingStrategy.ShallowMapping());
        return EmployeeEntityMapper.toDomain(entity, context);
    }

    @Override
    public List<Employee> listEmployeesByPetOfferings(List<Integer> petOfferingIds) {
        if (petOfferingIds == null || petOfferingIds.isEmpty()) {
            return List.of();
        }

        List<EmployeeEntity> entities = employeeRepository.findEmployeesByPetOfferingIds(
            petOfferingIds,
            petOfferingIds.size()
        );

        // Map without appointments to avoid circular references
        MappingContext context = new MappingContext(new MappingStrategy.ShallowMapping());
        return EmployeeEntityMapper.toDomainList(entities, context);
    }

    @Override
    public boolean existsById(Integer id) {
        return employeeRepository.existsById(id);
    }
}
