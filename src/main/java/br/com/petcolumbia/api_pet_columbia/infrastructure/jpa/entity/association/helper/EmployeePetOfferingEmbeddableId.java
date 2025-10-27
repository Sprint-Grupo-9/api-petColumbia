package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.helper;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeePetOfferingEmbeddableId implements Serializable {
    private Integer employeeId;
    private Integer petOfferingId;

    public EmployeePetOfferingEmbeddableId() {
    }

    public EmployeePetOfferingEmbeddableId(Integer employeeId, Integer petOfferingId) {
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
        EmployeePetOfferingEmbeddableId that = (EmployeePetOfferingEmbeddableId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(petOfferingId, that.petOfferingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, petOfferingId);
    }
}
