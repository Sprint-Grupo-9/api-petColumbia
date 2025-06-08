package br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos;

import br.com.petcolumbia.api_pet_columbia.dtos.responses.employeeDtos.EmployeeResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.petDtos.PetResponseDto;

import java.time.LocalDateTime;

public class AppointmentResponseDto {
    private Integer id;
    private PetResponseDto pet;
    private EmployeeResponseDto employee;
    private String services;
    private Double totalPrice;
    private String observations;
    private Boolean taxiService;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Boolean getTaxiService() {
        return taxiService;
    }

    public void setTaxiService(Boolean taxiService) {
        this.taxiService = taxiService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetResponseDto getPet() {
        return pet;
    }

    public void setPet(PetResponseDto pet) {
        this.pet = pet;
    }

    public void setEmployee(EmployeeResponseDto employee) {
        this.employee = employee;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public EmployeeResponseDto getEmployee() {
        return employee;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
