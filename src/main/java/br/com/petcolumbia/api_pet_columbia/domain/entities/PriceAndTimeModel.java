package br.com.petcolumbia.api_pet_columbia.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "price_and_time")
public class PriceAndTimeModel {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    @OneToMany(mappedBy = "priceAndTime")
    private List<AppointmentModel> appointments;

    private String petSize;
    private String petCoat;
    private Double price;
    private Double time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public List<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentModel> appointments) {
        this.appointments = appointments;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public String getPetCoat() {
        return petCoat;
    }

    public void setPetCoat(String petCoat) {
        this.petCoat = petCoat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
