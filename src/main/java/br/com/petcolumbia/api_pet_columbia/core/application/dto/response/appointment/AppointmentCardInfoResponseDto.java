package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment;

import java.time.LocalTime;

public class AppointmentCardInfoResponseDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private String ownerName;
    private String petName;
    private String petBreed;
    private String proceduresNames;
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

    public String getProceduresNames() {
        return proceduresNames;
    }

    public void setProceduresNames(String proceduresNames) {
        this.proceduresNames = proceduresNames;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
