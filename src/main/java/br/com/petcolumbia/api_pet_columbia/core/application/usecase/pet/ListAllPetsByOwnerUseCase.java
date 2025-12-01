package br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class ListAllPetsByOwnerUseCase {

    private final PetGateway petGateway;
    private final OwnerGateway ownerGateway;

    public ListAllPetsByOwnerUseCase(PetGateway petGateway, OwnerGateway ownerGateway) {
        this.petGateway = petGateway;
        this.ownerGateway = ownerGateway;
    }

    @Cacheable(cacheNames = "petsByOwner", key = "#ownerId")
    public List<Pet> execute(Integer ownerId) {
        // Valida se o owner existe
        ownerGateway.getOwnerById(ownerId);

        return petGateway.listAllPetsByOwner(ownerId);
    }
}