package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.repositories.EmployeeRepository;
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
