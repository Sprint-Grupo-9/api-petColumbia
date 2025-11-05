package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.others;

import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet_offering.PetOfferingRequest;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequest {
    private LocalDate date;
    private List<PetOfferingRequest> petOfferings;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<PetOfferingRequest> getPetOfferings() {
        return petOfferings;
    }

    public void setPetOfferings(List<PetOfferingRequest> petOfferings) {
        this.petOfferings = petOfferings;
    }
}