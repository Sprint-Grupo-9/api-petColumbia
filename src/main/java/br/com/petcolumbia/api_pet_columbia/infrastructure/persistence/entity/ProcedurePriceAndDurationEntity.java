package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "procedure_price_and_duration")
public class ProcedurePriceAndDurationEntity  {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private ProcedureEntity procedure;

    private String petSize;
    private String petCoat;
    private Double price;
    private Integer duration;

    public ProcedurePriceAndDurationEntity() {
    }

    public ProcedurePriceAndDurationEntity(Integer id, ProcedureEntity procedure, String petSize, String petCoat, Double price, Integer duration) {
        this.id = id;
        this.procedure = procedure;
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

    public ProcedureEntity getProcedure() {
        return procedure;
    }

    public void setProcedure(ProcedureEntity procedure) {
        this.procedure = procedure;
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
