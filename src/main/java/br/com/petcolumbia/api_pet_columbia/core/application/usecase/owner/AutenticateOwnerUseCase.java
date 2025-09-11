package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerAutenticateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;

public class AutenticateOwnerUseCase {

    private final OwnerGateway ownerGateway;

    public AutenticateOwnerUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(OwnerAutenticateCommand command) {

//        Owner owner = ownerGateway.findByEmail(command.email());
//
//        return ownerGateway.authenticate(command);
        return new Owner();
    }
}
