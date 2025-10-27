package br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering_price_and_duration.PetOfferingPriceAndDuration;

import java.util.List;

public interface PetOfferingPriceAndDurationGateway {
    PetOfferingPriceAndDuration findByPetOfferingIdAndPetSizeAndCoat(Integer petOfferingId, String petSize, String petCoat);
    List<PetOfferingWithPriceResponseDto> getPetOfferingsPricesByPetIdAndPetOfferingIds(Integer petId, List<Integer> petOfferingIds);
    Double calculateTotalPrice(List<Integer> petOfferingIds, String petSize, String petCoat);
    Integer calculateTotalDuration(List<Integer> petOfferingIds, String petSize, String petCoat);
}

