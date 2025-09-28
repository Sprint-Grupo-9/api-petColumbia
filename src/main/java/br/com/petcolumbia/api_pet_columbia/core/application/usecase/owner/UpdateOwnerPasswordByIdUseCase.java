package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerCredentialsGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;

public class UpdateOwnerPasswordByIdUseCase {
    private final OwnerGateway ownerGateway;
    private final OwnerCredentialsGateway ownerCredentialsGateway;

    public UpdateOwnerPasswordByIdUseCase(OwnerGateway ownerGateway, OwnerCredentialsGateway ownerCredentialsGateway) {
        this.ownerGateway = ownerGateway;
        this.ownerCredentialsGateway = ownerCredentialsGateway;
    }

    public Owner execute(Integer id, OwnerUpdatePasswordCommand command) {
        Owner owner = ownerGateway.getOwnerById(id);

        if(owner == null)
            throw new EntityNotFoundException("Usuário não encontrado");

        return ownerCredentialsGateway.updatePasswordById(id, command);
    }
}
