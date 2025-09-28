package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.helper;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeProcedureEmbeddableId implements Serializable {
    private Integer employeeId;
    private Integer procedureId;

    public EmployeeProcedureEmbeddableId() {
    }

    public EmployeeProcedureEmbeddableId(Integer employeeId, Integer procedureId) {
        this.employeeId = employeeId;
        this.procedureId = procedureId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProcedureEmbeddableId that = (EmployeeProcedureEmbeddableId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(procedureId, that.procedureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, procedureId);
    }
}
