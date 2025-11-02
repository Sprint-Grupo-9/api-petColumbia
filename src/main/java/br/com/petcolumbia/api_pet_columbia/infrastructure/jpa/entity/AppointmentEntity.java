package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    private String observations;
    private Boolean taxiService;

    @Column(name = "pet_offerings")
    private String petOfferings;

    private Double totalPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isFinished;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    public AppointmentEntity() {
    }

    public AppointmentEntity(Integer id, PetEntity pet, EmployeeEntity employee, String observations, Boolean taxiService, String petOfferings, Double totalPrice, LocalDateTime startDateTime, LocalDateTime endDateTime, Boolean isFinished, LocalDateTime createdAt, LocalDateTime lastUpdate) {
        this.id = id;
        this.pet = pet;
        this.employee = employee;
        this.observations = observations;
        this.taxiService = taxiService;
        this.petOfferings = petOfferings;
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

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
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

    public String getPetOfferings() {
        return petOfferings;
    }

    public void setPetOfferings(String petOfferingNames) {
        this.petOfferings = petOfferingNames;
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
