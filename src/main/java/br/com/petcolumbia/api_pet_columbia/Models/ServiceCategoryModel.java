package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

@Entity
public class ServiceCategoryModel {
    @Id
    private Integer id;
    private String name;
    private String description;

    @OneToOne(mappedBy = "service_category")
    private ServiceForecastModel serviceForecast;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceForecastModel getServiceForecastModel() {
        return serviceForecast;
    }
}
