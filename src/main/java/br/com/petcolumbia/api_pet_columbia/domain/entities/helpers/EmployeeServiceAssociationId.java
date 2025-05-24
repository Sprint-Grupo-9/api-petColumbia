package br.com.petcolumbia.api_pet_columbia.domain.entities.helpers;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeServiceAssociationId implements Serializable {
    private Integer employeeId;
    private Integer serviceId;

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeServiceAssociationId that = (EmployeeServiceAssociationId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, serviceId);
    }
}
