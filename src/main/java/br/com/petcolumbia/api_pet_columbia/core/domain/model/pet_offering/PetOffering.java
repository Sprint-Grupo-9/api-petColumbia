package br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.EmployeePetOfferingAssociation;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering_price_and_duration.PetOfferingPriceAndDuration;

import java.util.List;

public class PetOffering {
    private Integer id;
    private String name;
    private String description;
    private List<EmployeePetOfferingAssociation> employeePetOfferings;
    private List<PetOfferingPriceAndDuration> pricesAndDurations;

    public PetOffering() {
    }

    public PetOffering(Integer id, String name, String description, List<EmployeePetOfferingAssociation> employeePetOfferings, List<PetOfferingPriceAndDuration> pricesAndDurations) {
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

    public List<EmployeePetOfferingAssociation> getEmployeePetOfferings() {
        return employeePetOfferings;
    }

    public void setEmployeePetOfferings(List<EmployeePetOfferingAssociation> employeePetOfferings) {
        this.employeePetOfferings = employeePetOfferings;
    }

    public List<PetOfferingPriceAndDuration> getPricesAndDurations() {
        return pricesAndDurations;
    }

    public void setPricesAndDurations(List<PetOfferingPriceAndDuration> pricesAndDurations) {
        this.pricesAndDurations = pricesAndDurations;
    }
}
