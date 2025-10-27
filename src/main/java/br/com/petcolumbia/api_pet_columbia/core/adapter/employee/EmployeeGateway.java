package br.com.petcolumbia.api_pet_columbia.core.adapter.employee;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;

import java.util.List;

public interface EmployeeGateway {
    Employee findEmployeeById(Integer employeeId);
    List<Employee> listEmployeesByPetOfferings(List<Integer> petOfferingIds);
    boolean existsById(Integer id);
}

