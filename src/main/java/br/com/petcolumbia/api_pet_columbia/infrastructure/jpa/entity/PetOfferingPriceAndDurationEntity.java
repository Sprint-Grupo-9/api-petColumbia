package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pet_offering_price_and_duration")
public class PetOfferingPriceAndDurationEntity  {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_offering_id")
    private PetOfferingEntity petOffering;

    private String petSize;
    private String petCoat;
    private Double price;
    private Integer duration;

    public PetOfferingPriceAndDurationEntity() {
    }

    public PetOfferingPriceAndDurationEntity(Integer id, PetOfferingEntity petOffering, String petSize, String petCoat, Double price, Integer duration) {
        this.id = id;
        this.petOffering = petOffering;
        this.petSize = petSize;
        this.petCoat = petCoat;
        this.price = price;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetOfferingEntity getPetOffering() {
        return petOffering;
    }

    public void setPetOffering(PetOfferingEntity petOffering) {
        this.petOffering = petOffering;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
