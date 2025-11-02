package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering;

public class PetOfferingWithPriceResponseDto {
    private Integer id;
    private String petOfferingName;
    private Double price;

    public PetOfferingWithPriceResponseDto() {
    }

    public PetOfferingWithPriceResponseDto(Integer id, String petOfferingName, Double price) {
        this.id = id;
        this.petOfferingName = petOfferingName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPetOfferingName() {
        return petOfferingName;
    }

    public void setPetOfferingName(String petOfferingName) {
        this.petOfferingName = petOfferingName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
