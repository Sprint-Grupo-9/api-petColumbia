package br.com.petcolumbia.api_pet_columbia.old.services;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.old.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    public List<EmployeeModel> listEmployeesServices(List<Integer> servicesIds){
        return employeeRepository.findEmployeesByServiceIds(servicesIds);
    }

    public EmployeeModel findEmployeeById(Integer employeeId){
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
    }
}
