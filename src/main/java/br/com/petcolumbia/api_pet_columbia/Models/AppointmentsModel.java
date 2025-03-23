package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AppointmentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetModel pet;

    //Employee id

    @ManyToOne
    @JoinColumn(name = "service_forecast_id")
    private ServiceForecastModel serviceForecast;

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

    public ServiceForecastModel getServiceForecast() {
        return serviceForecast;
    }

    public void setServiceForecast(ServiceForecastModel serviceForecast) {
        this.serviceForecast = serviceForecast;
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
