package br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee;

import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;

public class FindEmployeeByIdUseCase {

    private final EmployeeGateway employeeGateway;

    public FindEmployeeByIdUseCase(EmployeeGateway employeeGateway) {
        this.employeeGateway = employeeGateway;
    }

    public Employee execute(Integer employeeId) {
        return employeeGateway.findEmployeeById(employeeId);
    }
}

