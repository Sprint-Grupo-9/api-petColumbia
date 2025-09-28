package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;

public class UpdateOwnerUseCase {
    private final OwnerGateway ownerGateway;

    public UpdateOwnerUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(Integer id, OwnerUpdateCommand command) {
        Owner owner = ownerGateway.getOwnerById(id);

        if(owner == null)
            throw new EntityNotFoundException("Usuário não encontrado pelo id: " + id);

        owner.getPersonalInfo().setPhoneNumber(command.phoneNumber());
        owner.setEmail(command.email());
        owner.getAdress().setCep(command.cep());
        owner.getAdress().setNeighborhood(command.neighborhood());
        owner.getAdress().setStreet(command.street());
        owner.getAdress().setNumber(command.number());
        owner.getAdress().setComplement(command.complement());

        return ownerGateway.updateOwnerById(id, command);
    }
}
