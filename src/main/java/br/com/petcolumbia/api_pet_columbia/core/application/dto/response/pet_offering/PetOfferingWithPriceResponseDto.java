package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering;

public class PetOfferingWithPriceResponseDto {
    private Integer id;
    private String procedureName;
    private Double price;

    public PetOfferingWithPriceResponseDto() {
    }

    public PetOfferingWithPriceResponseDto(Integer id, String procedureName, Double price) {
        this.id = id;
        this.procedureName = procedureName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
