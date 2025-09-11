package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.Adress;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityConflictException;

public class CreateOwnerUseCase {

    private final OwnerGateway ownerGateway;

    public CreateOwnerUseCase(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    public Owner execute(OwnerCreateCommand command) {
        if(ownerGateway.isDuplicateFields(command.email(),command.cpf(),command.phoneNumber(),null))
            throw new EntityConflictException("Já existe um usuário com o e-mail, CPF ou telefone informados.");

        Owner owner = new Owner();

        owner.setName(command.name());
        owner.setEmail(command.email());
        owner.setPersonalInfo(new PersonalInfo(
                command.cpf(),
                command.phoneNumber()
        ));
        owner.setEmail(command.email());
        owner.setPassword(command.password());
        owner.setAdress(new Adress(
                command.cep(),
                command.neighborhood(),
                command.street(),
                command.number(),
                command.complement()
        ));
        owner.setAdm(false);

        return ownerGateway.create(owner);
    }
}
