package br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard;

import java.time.LocalDate;

public class LeastServiceResponseDto {
    private String serviceName;
    private Long count;
    private LocalDate start;
    private LocalDate end;

    public LeastServiceResponseDto(String serviceName, Long count, LocalDate start, LocalDate end) {
        this.serviceName = serviceName;
        this.count = count;
        this.start = start;
        this.end = end;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
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
}
