package br.com.petcolumbia.api_pet_columbia.dtos.responses;

import java.time.LocalTime;

public class BusyTime {
    private LocalTime startDateTime;
    private LocalTime endDateTime;

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
