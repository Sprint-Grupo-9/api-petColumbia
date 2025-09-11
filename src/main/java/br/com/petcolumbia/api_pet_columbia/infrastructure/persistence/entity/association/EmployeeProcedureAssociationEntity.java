package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.association;

import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.EmployeeEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.ProcedureEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.association.helper.EmployeeProcedureEmbeddableId;

import jakarta.persistence.*;

@Entity
@Table(name = "employees_has_procedures")
public class EmployeeProcedureAssociationEntity {
    @EmbeddedId
    private EmployeeProcedureEmbeddableId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ManyToOne
    @MapsId("procedureId")
    @JoinColumn(name = "procedure_id")
    private ProcedureEntity procedure;

    public EmployeeProcedureAssociationEntity() {
    }
    public EmployeeProcedureAssociationEntity(EmployeeProcedureEmbeddableId id, EmployeeEntity employee, ProcedureEntity procedure) {
        this.id = id;
        this.employee = employee;
        this.procedure = procedure;
    }

    public EmployeeProcedureEmbeddableId getId() {
        return id;
    }

    public void setId(EmployeeProcedureEmbeddableId id) {
        this.id = id;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public ProcedureEntity getProcedure() {
        return procedure;
    }

    public void setProcedure(ProcedureEntity procedure) {
        this.procedure = procedure;
    }
}
