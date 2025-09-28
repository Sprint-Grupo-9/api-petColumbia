package br.com.petcolumbia.api_pet_columbia.core.domain.model.procedure_price_and_duration;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.procedure.Procedure;

public class ProcedurePriceAndDuration {
    private Integer id;
    private Procedure procedure;
    private String petSize;
    private String petCoat;
    private Double price;
    private Integer duration;

    public ProcedurePriceAndDuration() {
    }

    public ProcedurePriceAndDuration(Integer id, Procedure procedure, String petSize, String petCoat, Double price, Integer duration) {
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

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
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
