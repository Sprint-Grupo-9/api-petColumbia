package br.com.petcolumbia.api_pet_columbia.core.domain.associations;

import br.com.petcolumbia.api_pet_columbia.core.domain.associations.valueobject.EmployeeProcedureAssociationId;
import br.com.petcolumbia.api_pet_columbia.core.domain.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.core.domain.procedure.Procedure;

public class EmployeeProcedureAssociation {
    private EmployeeProcedureAssociationId id;
    private Employee employee;
    private Procedure service;

    public EmployeeProcedureAssociation() {
    }

    public EmployeeProcedureAssociation(EmployeeProcedureAssociationId id, Employee employee, Procedure service) {
        this.id = id;
        this.employee = employee;
        this.service = service;
    }

    public EmployeeProcedureAssociationId getId() {
        return id;
    }

    public void setId(EmployeeProcedureAssociationId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Procedure getService() {
        return service;
    }

    public void setService(Procedure service) {
        this.service = service;
    }
}
