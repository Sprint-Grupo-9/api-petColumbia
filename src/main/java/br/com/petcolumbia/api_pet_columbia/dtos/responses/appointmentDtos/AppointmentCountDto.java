package br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos;

import java.time.LocalDate;

public class AppointmentCountDto {
    private LocalDate date;
    private Long count;

    public AppointmentCountDto(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

