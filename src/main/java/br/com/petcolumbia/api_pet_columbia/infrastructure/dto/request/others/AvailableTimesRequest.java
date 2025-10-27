package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.others;

import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet_offering.PetOfferingRequest;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequest {
    private LocalDate date;
    private List<PetOfferingRequest> procedures;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<PetOfferingRequest> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<PetOfferingRequest> procedures) {
        this.procedures = procedures;
    }
}