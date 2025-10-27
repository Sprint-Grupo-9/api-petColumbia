package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.EmployeePetOfferingAssociationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "pet_offering")
public class PetOfferingEntity {
    @Id
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "petOffering")
    private List<EmployeePetOfferingAssociationEntity> employeePetOfferings;

    @OneToMany(mappedBy = "petOffering")
    private List<PetOfferingPriceAndDurationEntity> pricesAndDurations;

    public PetOfferingEntity() {
    }

    public PetOfferingEntity(Integer id, String name, String description, List<EmployeePetOfferingAssociationEntity> employeePetOfferings, List<PetOfferingPriceAndDurationEntity> pricesAndDurations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employeePetOfferings = employeePetOfferings;
        this.pricesAndDurations = pricesAndDurations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeePetOfferingAssociationEntity> getEmployeePetOfferings() {
        return employeePetOfferings;
    }

    public void setEmployeePetOfferings(List<EmployeePetOfferingAssociationEntity> employeePetOfferings) {
        this.employeePetOfferings = employeePetOfferings;
    }

    public List<PetOfferingPriceAndDurationEntity> getPricesAndDurations() {
        return pricesAndDurations;
    }

    public void setPricesAndDurations(List<PetOfferingPriceAndDurationEntity> pricesAndDurations) {
        this.pricesAndDurations = pricesAndDurations;
    }
}
