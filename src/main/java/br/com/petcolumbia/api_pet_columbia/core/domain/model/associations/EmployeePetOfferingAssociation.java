package br.com.petcolumbia.api_pet_columbia.core.domain.model.associations;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.valueobject.EmployeePetOfferingAssociationId;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;

public class EmployeePetOfferingAssociation {
    private EmployeePetOfferingAssociationId id;
    private Employee employee;
    private PetOffering petOffering;

    public EmployeePetOfferingAssociation() {
    }

    public EmployeePetOfferingAssociation(EmployeePetOfferingAssociationId id, Employee employee, PetOffering procedure) {
        this.id = id;
        this.employee = employee;
        this.petOffering = procedure;
    }

    public EmployeePetOfferingAssociationId getId() {
        return id;
    }

    public void setId(EmployeePetOfferingAssociationId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PetOffering getPetOffering() {
        return petOffering;
    }

    public void setPetOffering(PetOffering petOffering) {
        this.petOffering = petOffering;
    }
}
