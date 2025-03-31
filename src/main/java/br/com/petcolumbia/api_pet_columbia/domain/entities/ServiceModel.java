package br.com.petcolumbia.api_pet_columbia.domain.entities;

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
    private List<PriceAndTimeModel> pricesAndTimes;

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

    public List<EmployeeServiceAssociationModel> getEmployeeServices() {
        return employeeServices;
    }

    public void setEmployeeServices(List<EmployeeServiceAssociationModel> employeeServices) {
        this.employeeServices = employeeServices;
    }

    public List<PriceAndTimeModel> getPricesAndTimes() {
        return pricesAndTimes;
    }

    public void setPricesAndTimes(List<PriceAndTimeModel> pricesAndTimes) {
        this.pricesAndTimes = pricesAndTimes;
    }
}
