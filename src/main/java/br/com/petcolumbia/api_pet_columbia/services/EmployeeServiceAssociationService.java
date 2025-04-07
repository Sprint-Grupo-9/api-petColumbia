package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IEmployeeServiceAssociationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceAssociationService {

    private final IEmployeeServiceAssociationRepository employeeServiceAssociationRepository;

    public EmployeeServiceAssociationService(IEmployeeServiceAssociationRepository repository) {
        this.employeeServiceAssociationRepository = repository;
    }

    public List<EmployeeModel> listEmployeesServices(List<Integer> servicesIds){
        return employeeServiceAssociationRepository.findEmployeesByServiceIds(servicesIds);
    }
}
