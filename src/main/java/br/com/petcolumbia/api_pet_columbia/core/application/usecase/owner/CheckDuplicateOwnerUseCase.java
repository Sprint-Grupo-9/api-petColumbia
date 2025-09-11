package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;

public class CheckDuplicateOwnerUseCase {
    private final OwnerGateway ownerGateway;

    public CheckDuplicateOwnerUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public boolean execute(String email, String cpf, String phoneNumber, Integer id) {
        return ownerGateway.isDuplicateFields(email, cpf, phoneNumber, id);
    }
}
