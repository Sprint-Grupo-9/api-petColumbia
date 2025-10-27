package br.com.petcolumbia.api_pet_columbia.core.adapter.pet;

import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;

import java.util.List;

public interface PetGateway {
    Pet create(Pet pet, Integer ownerId);
    Pet findPetById(Integer id, Integer ownerId);
    List<Pet> listAllPetsByOwner(Integer ownerId);
    void deletePetById(Integer id);
    Pet updatePetById(Integer id, PetUpdateCommand command);
    boolean existsById(Integer id);
}

