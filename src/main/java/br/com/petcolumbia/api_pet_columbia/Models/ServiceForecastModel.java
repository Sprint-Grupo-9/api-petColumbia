package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ServiceForecastModel {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "service_category_id")
    private ServiceCategoryModel serviceCategory;

    private String petSize;
    private String petCoat;
    private Double servicePrice;
    private Double serviceDurationInMin;

    @OneToMany(mappedBy = "service_forecast")
    private List<AppointmentsModel> appointments;

    public Integer getId() {
        return id;
    }

    public ServiceCategoryModel getServiceCategory() {
        return serviceCategory;
    }

    public String getPetSize() {
        return petSize;
    }

    public String getPetCoat() {
        return petCoat;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public Double getServiceDurationInMin() {
        return serviceDurationInMin;
    }

    public List<AppointmentsModel> getAppointments() {
        return appointments;
    }
}
