package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard;

import java.time.LocalDate;

public class LeastServiceResponseDto {
    private String procedureName;
    private Long count;
    private LocalDate start;
    private LocalDate end;

    public LeastServiceResponseDto(String procedureName, Long count, LocalDate start, LocalDate end) {
        this.procedureName = procedureName;
        this.count = count;
        this.start = start;
        this.end = end;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
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
