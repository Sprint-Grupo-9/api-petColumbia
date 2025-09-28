package br.com.petcolumbia.api_pet_columbia.old.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "service")
public class ServiceModel {
    @Id
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "service")
    private List<EmployeeServiceAssociationModel> employeeServices;

    @OneToMany(mappedBy = "service")
    private List<ServicePriceAndDurationModel> pricesAndDurations;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
