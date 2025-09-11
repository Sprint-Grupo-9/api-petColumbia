package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;

public class DeleteOwnerByIdUseCase {
    private final OwnerGateway ownerGateway;

    public DeleteOwnerByIdUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public void execute(Integer id) {
        ownerGateway.deleteOwnerById(id);
    }
}
