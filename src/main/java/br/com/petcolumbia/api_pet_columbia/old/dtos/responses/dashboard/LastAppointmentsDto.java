package br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard;

import java.time.LocalDate;
import java.time.LocalTime;

public class LastAppointmentsDto {
    private Integer id; //petId or ownerId
    private Integer appointmentId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String servicesNames;
    private Double price;

    public LastAppointmentsDto() {
    }

    public LastAppointmentsDto(Integer id, Integer appointmentId, LocalDate date, LocalTime start, LocalTime end, String servicesNames, Double price) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.servicesNames = servicesNames;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getServicesNames() {
        return servicesNames;
    }

    public void setServicesNames(String servicesNames) {
        this.servicesNames = servicesNames;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
