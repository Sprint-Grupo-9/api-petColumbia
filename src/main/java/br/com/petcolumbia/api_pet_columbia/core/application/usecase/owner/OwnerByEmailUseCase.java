package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;

public class OwnerByEmailUseCase {

    private  final OwnerGateway ownerGateway;

    public OwnerByEmailUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(String email) {
        return ownerGateway.getOwnerByEmail(email);
    }
}
