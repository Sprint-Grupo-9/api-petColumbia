package br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;

import java.util.List;

public interface PetOfferingGateway {
    List<PetOffering> listAllPetOfferings();
    PetOffering findPetOfferingById(Integer petOfferingId);
    String getPetOfferingsNamesByIds(List<Integer> petOfferingIds);
    List<Integer> getPetOfferingIds(List<PetOffering> petOfferings);
}
