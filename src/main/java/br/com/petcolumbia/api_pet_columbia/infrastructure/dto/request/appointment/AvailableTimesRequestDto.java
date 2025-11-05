package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequestDto {
    @NotNull(message = "A data é obrigatória")
    private LocalDate date;

    @NotNull(message = "A lista de procedimentos é obrigatória")
    @NotEmpty(message = "A lista de procedimentos não pode estar vazia")
    private List<Integer> petOfferingIds;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getPetOfferingIds() {
        return petOfferingIds;
    }

    public void setPetOfferingIds(List<Integer> petOfferingIds) {
        this.petOfferingIds = petOfferingIds;
    }
}

