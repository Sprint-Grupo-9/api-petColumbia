package br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.valueobject;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeProcedureAssociationId implements Serializable {
    private Integer employeeId;
    private Integer procedureId;

    public EmployeeProcedureAssociationId() {
    }

    public EmployeeProcedureAssociationId(Integer employeeId, Integer procedureId) {
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
        EmployeeProcedureAssociationId that = (EmployeeProcedureAssociationId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(procedureId, that.procedureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, procedureId);
    }
}

