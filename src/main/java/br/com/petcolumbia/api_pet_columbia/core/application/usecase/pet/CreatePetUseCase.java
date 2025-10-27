package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.mapper.PetCoreMapper;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;

public class CreatePetUseCase {

    private final PetGateway petGateway;
    private final OwnerGateway ownerGateway;

    public CreatePetUseCase(PetGateway petGateway, OwnerGateway ownerGateway) {
        this.petGateway = petGateway;
        this.ownerGateway = ownerGateway;
    }

    public Pet execute(Integer ownerId, PetCreateCommand command) {
        // Valida se o owner existe
        ownerGateway.getOwnerById(ownerId);

        Pet pet = PetCoreMapper.of(command);
        return petGateway.create(pet, ownerId);
    }
}