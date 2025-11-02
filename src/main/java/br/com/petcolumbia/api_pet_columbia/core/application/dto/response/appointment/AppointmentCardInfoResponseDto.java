package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment;

import java.time.LocalTime;

public class AppointmentCardInfoResponseDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private String ownerName;
    private String petName;
    private String petBreed;
    private String petOfferingNames;
    private Double price;
    private Boolean taxiService;

    public Boolean getTaxiService() {
        return taxiService;
    }

    public void setTaxiService(Boolean taxiService) {
        this.taxiService = taxiService;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetOfferingNames() {
        return petOfferingNames;
    }

    public void setPetOfferingNames(String petOfferingNames) {
        this.petOfferingNames = petOfferingNames;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
