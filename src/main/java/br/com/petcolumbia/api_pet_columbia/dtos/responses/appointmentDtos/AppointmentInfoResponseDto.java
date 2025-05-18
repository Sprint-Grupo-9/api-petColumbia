package br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos;

import br.com.petcolumbia.api_pet_columbia.dtos.responses.employeeDtos.EmployeeResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.petDtos.PetOwnerInfoResponseDto;

import java.time.LocalDateTime;

public class AppointmentInfoResponseDto {
    private Integer id;
    private PetOwnerInfoResponseDto pet;
    private EmployeeResponseDto employee;
    private String services;
    private Double totalPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetOwnerInfoResponseDto getPet() {
        return pet;
    }

    public void setPet(PetOwnerInfoResponseDto pet) {
        this.pet = pet;
    }

    public EmployeeResponseDto getEmployee() {
        return employee;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
