package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard;

import java.time.LocalDate;
import java.time.LocalTime;

public class TopPetOfferingsTimingResponse {
    private LocalDate start;
    private LocalDate end;
    private LocalTime hour;
    private long count;

    public TopPetOfferingsTimingResponse() {
    }

    public TopPetOfferingsTimingResponse(LocalDate start, LocalDate end, LocalTime hour, long count) {
        this.start = start;
        this.end = end;
        this.hour = hour;
        this.count = count;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
