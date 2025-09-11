package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityNotFoundException;

public class OwnerByIdUseCase {
    private final OwnerGateway ownerGateway;

    public OwnerByIdUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(Integer id) {
        Owner owner =  ownerGateway.getOwnerById(id);

        if(owner == null)
            throw new EntityNotFoundException("Usuário não encontrado pelo id: " + id);

        return owner;
    }

}
