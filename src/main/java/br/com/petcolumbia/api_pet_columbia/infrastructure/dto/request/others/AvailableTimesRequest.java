package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.others;

import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.procedure.ProcedureRequest;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequest {
    private LocalDate date;
    private List<ProcedureRequest> procedures;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<ProcedureRequest> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureRequest> procedures) {
        this.procedures = procedures;
    }
}