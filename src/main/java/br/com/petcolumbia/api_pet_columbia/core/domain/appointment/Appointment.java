package br.com.petcolumbia.api_pet_columbia.core.domain.appointment;

import br.com.petcolumbia.api_pet_columbia.core.domain.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.core.domain.pet.Pet;
import java.time.LocalDateTime;

public class Appointment {
    private Integer id;
    private Pet pet;
    private Employee employee;
    private String observations;
    private Boolean taxiService;
    private String services;
    private Double totalPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isFinished;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    public Appointment() {
    }

    public Appointment(Integer id, Pet pet, Employee employee, String observations, Boolean taxiService, String services, Double totalPrice, LocalDateTime startDateTime, LocalDateTime endDateTime, Boolean isFinished, LocalDateTime createdAt, LocalDateTime lastUpdate) {
        this.id = id;
        this.pet = pet;
        this.employee = employee;
        this.observations = observations;
        this.taxiService = taxiService;
        this.services = services;
        this.totalPrice = totalPrice;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isFinished = isFinished;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

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

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
