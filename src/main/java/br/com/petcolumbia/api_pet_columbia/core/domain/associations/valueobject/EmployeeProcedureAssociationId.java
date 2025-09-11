package br.com.petcolumbia.api_pet_columbia.core.domain.associations.valueobject;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeProcedureAssociationId implements Serializable {
    private Integer employeeId;
    private Integer serviceId;

    public EmployeeProcedureAssociationId() {
    }

    public EmployeeProcedureAssociationId(Integer employeeId, Integer serviceId) {
        this.employeeId = employeeId;
        this.serviceId = serviceId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProcedureAssociationId that = (EmployeeProcedureAssociationId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, serviceId);
    }
}

