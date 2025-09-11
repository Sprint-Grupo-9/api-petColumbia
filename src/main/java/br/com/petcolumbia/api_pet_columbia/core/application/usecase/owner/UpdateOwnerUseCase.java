package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;

public class UpdateOwnerUseCase {
    private final OwnerGateway ownerGateway;

    public UpdateOwnerUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(Integer id, Owner owner) {
        return ownerGateway.updateOwnerById(id, owner);
    }
}
