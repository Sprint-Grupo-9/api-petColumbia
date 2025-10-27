package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;

public class UpdatePetByIdUseCase {

    private final PetGateway petGateway;

    public UpdatePetByIdUseCase(PetGateway petGateway) {
        this.petGateway = petGateway;
    }

    public Pet execute(Integer id, PetUpdateCommand command) {
        return petGateway.updatePetById(id, command);
    }
}