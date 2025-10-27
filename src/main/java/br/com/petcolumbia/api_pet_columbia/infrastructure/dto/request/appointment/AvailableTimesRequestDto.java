package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequestDto {
    private LocalDate date;
    private List<Integer> procedureIds;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getProcedureIds() {
        return procedureIds;
    }

    public void setProcedureIds(List<Integer> procedureIds) {
        this.procedureIds = procedureIds;
    }
}

