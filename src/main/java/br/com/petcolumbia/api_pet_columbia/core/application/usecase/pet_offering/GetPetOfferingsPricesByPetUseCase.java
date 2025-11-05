package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingWithPriceResponseDto;

import java.util.List;

public class GetPetOfferingsPricesByPetUseCase {

    private final PetOfferingPriceAndDurationGateway priceAndDurationGateway;

    public GetPetOfferingsPricesByPetUseCase(PetOfferingPriceAndDurationGateway priceAndDurationGateway) {
        this.priceAndDurationGateway = priceAndDurationGateway;
    }

    public List<PetOfferingWithPriceResponseDto> execute(Integer petId, List<Integer> petOfferingIds) {
        return priceAndDurationGateway.getPetOfferingsPricesByPetIdAndPetOfferingIds(petId, petOfferingIds);
    }
}

