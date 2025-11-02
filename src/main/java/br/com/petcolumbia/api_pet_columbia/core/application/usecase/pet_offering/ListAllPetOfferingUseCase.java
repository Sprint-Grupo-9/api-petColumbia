package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;

import java.util.List;

public class ListAllPetOfferingUseCase {

    private final PetOfferingGateway petOfferingGateway;

    public ListAllPetOfferingUseCase(PetOfferingGateway petOfferingGateway) {
        this.petOfferingGateway = petOfferingGateway;
    }

    public List<PetOffering> execute() {
        return petOfferingGateway.listAllPetOfferings();
    }
}

