package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastAppointmentsListDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.employee.EmployeeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet.PetOwnerInfoResponseDto;

import java.time.LocalDateTime;

public class AppointmentInfoResponseDto {
    private Integer id;
    private PetOwnerInfoResponseDto pet;
    private EmployeeResponseDto employee;
    private String petOfferingNames;
    private Double totalPrice;
    private String observations;
    private Boolean taxiService;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LastAppointmentsListDto lastPetAppointments;
    private LastAppointmentsListDto lastOwnerAppointments;

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

    public String getPetOfferingNames() {
        return petOfferingNames;
    }

    public void setPetOfferingNames(String petOfferingNames) {
        this.petOfferingNames = petOfferingNames;
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

    public LastAppointmentsListDto getLastPetAppointments() {
        return lastPetAppointments;
    }

    public void setLastPetAppointments(LastAppointmentsListDto lastPetAppointments) {
        this.lastPetAppointments = lastPetAppointments;
    }

    public LastAppointmentsListDto getLastOwnerAppointments() {
        return lastOwnerAppointments;
    }

    public void setLastOwnerAppointments(LastAppointmentsListDto lastOwnerAppointments) {
        this.lastOwnerAppointments = lastOwnerAppointments;
    }
}
