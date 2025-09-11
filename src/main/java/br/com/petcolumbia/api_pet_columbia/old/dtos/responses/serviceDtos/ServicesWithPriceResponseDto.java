package br.com.petcolumbia.api_pet_columbia.old.dtos.responses.serviceDtos;

public class ServicesWithPriceResponseDto {
    private Integer id;
    private String serviceName;
    private Double price;

    public ServicesWithPriceResponseDto() {
    }

    public ServicesWithPriceResponseDto(Integer id, String serviceName, Double price) {
        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
