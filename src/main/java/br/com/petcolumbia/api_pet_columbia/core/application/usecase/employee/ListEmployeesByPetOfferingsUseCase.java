package br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee;

import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;

import java.util.List;

public class ListEmployeesByPetOfferingsUseCase {

    private final EmployeeGateway employeeGateway;

    public ListEmployeesByPetOfferingsUseCase(EmployeeGateway employeeGateway) {
        this.employeeGateway = employeeGateway;
    }

    public List<Employee> execute(List<Integer> petOfferingIds) {
        return employeeGateway.listEmployeesByPetOfferings(petOfferingIds);
    }
}

