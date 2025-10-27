package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment;

import java.time.LocalTime;

public class BusyTimeResponseDto {
    private LocalTime startDateTime;
    private LocalTime endDateTime;

    public BusyTimeResponseDto() {
    }

    public BusyTimeResponseDto(LocalTime startDateTime, LocalTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}

