package br.com.petcolumbia.api_pet_columbia.core.domain.model.associations;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.valueobject.EmployeeProcedureAssociationId;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.procedure.Procedure;

public class EmployeeProcedureAssociation {
    private EmployeeProcedureAssociationId id;
    private Employee employee;
    private Procedure procedure;

    public EmployeeProcedureAssociation() {
    }

    public EmployeeProcedureAssociation(EmployeeProcedureAssociationId id, Employee employee, Procedure procedure) {
        this.id = id;
        this.employee = employee;
        this.procedure = procedure;
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

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
}
