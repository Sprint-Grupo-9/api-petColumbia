package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;

public class DeleteOwnerByIdUseCase {
    private final OwnerGateway ownerGateway;

    public DeleteOwnerByIdUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public void execute(Integer id) {
        if (ownerGateway.existsById(id))
            throw new EntityNotFoundException("Não encontrado usuário com id:" + id);
        
        ownerGateway.deleteOwnerById(id);
    }
}
