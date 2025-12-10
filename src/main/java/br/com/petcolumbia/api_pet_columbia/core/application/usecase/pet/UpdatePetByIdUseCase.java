package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import org.springframework.cache.annotation.CacheEvict;

public class UpdatePetByIdUseCase {

    private final PetGateway petGateway;

    public UpdatePetByIdUseCase(PetGateway petGateway) {
        this.petGateway = petGateway;
    }

    @CacheEvict(cacheNames = "petsByOwner", allEntries = true)
    public Pet execute(Integer id, PetUpdateCommand command) {
        return petGateway.updatePetById(id, command);
    }
}