package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard;

import java.time.LocalDate;

public class TopPetOfferingResponseDto {
    private String petOfferingName;
    private Long count;
    private LocalDate start;
    private LocalDate end;

    public TopPetOfferingResponseDto(String petOfferingName, Long count, LocalDate start, LocalDate end) {
        this.petOfferingName = petOfferingName;
        this.count = count;
        this.start = start;
        this.end = end;
    }

    public String getPetOfferingName() {
        return petOfferingName;
    }

    public void setPetOfferingName(String petOfferingName) {
        this.petOfferingName = petOfferingName;
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
