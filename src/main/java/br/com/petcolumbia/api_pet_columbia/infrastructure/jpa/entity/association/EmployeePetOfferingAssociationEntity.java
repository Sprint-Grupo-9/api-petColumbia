package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.EmployeeEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.helper.EmployeePetOfferingEmbeddableId;
import jakarta.persistence.*;

@Entity
@Table(name = "employees_has_pet_offerings")
public class EmployeePetOfferingAssociationEntity {
    @EmbeddedId
    private EmployeePetOfferingEmbeddableId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ManyToOne
    @MapsId("petOfferingId")
    @JoinColumn(name = "pet_offering_id")
    private PetOfferingEntity petOffering;

    public EmployeePetOfferingAssociationEntity() {
    }
    public EmployeePetOfferingAssociationEntity(EmployeePetOfferingEmbeddableId id, EmployeeEntity employee, PetOfferingEntity petOffering) {
        this.id = id;
        this.employee = employee;
        this.petOffering = petOffering;
    }

    public EmployeePetOfferingEmbeddableId getId() {
        return id;
    }

    public void setId(EmployeePetOfferingEmbeddableId id) {
        this.id = id;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public PetOfferingEntity getPetOffering() {
        return petOffering;
    }

    public void setPetOffering(PetOfferingEntity petOffering) {
        this.petOffering = petOffering;
    }
}
