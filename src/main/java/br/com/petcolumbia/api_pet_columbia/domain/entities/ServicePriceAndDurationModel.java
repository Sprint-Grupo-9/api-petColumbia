package br.com.petcolumbia.api_pet_columbia.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "service_price_and_time")
public class ServicePriceAndDurationModel {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    private String petSize;
    private String petCoat;
    private Double price;
    private Integer time;

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
