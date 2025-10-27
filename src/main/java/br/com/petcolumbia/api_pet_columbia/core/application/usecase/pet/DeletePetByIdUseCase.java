package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;

public class DeletePetByIdUseCase {

    private final PetGateway petGateway;

    public DeletePetByIdUseCase(PetGateway petGateway) {
        this.petGateway = petGateway;
    }

    public void execute(Integer id) {
        if (!petGateway.existsById(id)) {
            throw new EntityNotFoundException("Pet não encontrado");
        }

        petGateway.deletePetById(id);
    }
}