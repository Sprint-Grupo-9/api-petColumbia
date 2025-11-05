package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;

public class FindPetByIdUseCase {

    private final PetGateway petGateway;

    public FindPetByIdUseCase(PetGateway petGateway) {
        this.petGateway = petGateway;
    }

    public Pet execute(Integer id, Integer ownerId) {
        return petGateway.findPetById(id, ownerId);
    }
}