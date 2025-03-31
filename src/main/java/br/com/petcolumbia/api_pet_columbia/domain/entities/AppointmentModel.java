package br.com.petcolumbia.api_pet_columbia.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetModel pet;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    @ManyToOne
    @JoinColumn(name = "price_and_time_id")
    private PriceAndTimeModel priceAndTime;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isFinished;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public PriceAndTimeModel getPriceAndTime() {
        return priceAndTime;
    }

    public void setPriceAndTime(PriceAndTimeModel priceAndTime) {
        this.priceAndTime = priceAndTime;
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
