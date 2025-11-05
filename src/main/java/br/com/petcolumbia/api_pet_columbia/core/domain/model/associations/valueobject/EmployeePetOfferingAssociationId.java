package br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.valueobject;

import java.io.Serializable;
import java.util.Objects;

public class EmployeePetOfferingAssociationId implements Serializable {
    private Integer employeeId;
    private Integer petOfferingId;

    public EmployeePetOfferingAssociationId() {
    }

    public EmployeePetOfferingAssociationId(Integer employeeId, Integer petOfferingId) {
        this.employeeId = employeeId;
        this.petOfferingId = petOfferingId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPetOfferingId() {
        return petOfferingId;
    }

    public void setPetOfferingId(Integer petOfferingId) {
        this.petOfferingId = petOfferingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePetOfferingAssociationId that = (EmployeePetOfferingAssociationId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(petOfferingId, that.petOfferingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, petOfferingId);
    }
}

