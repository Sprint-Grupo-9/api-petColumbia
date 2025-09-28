package br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.mapper.OwnerCoreMapper;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.core.application.service.OwnerValidationService;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;

public class CreateOwnerUseCase {

    private final OwnerGateway ownerGateway;
    private final OwnerValidationService validationService;

    public CreateOwnerUseCase(OwnerGateway ownerGateway, OwnerValidationService validationService) {
        this.ownerGateway = ownerGateway;
        this.validationService = validationService;
    }

    public Owner execute(OwnerCreateCommand command) {
        if(validationService.checkDuplicateFieldsOnCreate(command.email(),command.cpf(),command.phoneNumber()))
            throw new EntityConflictException("Já existe um usuário com o e-mail, CPF ou telefone informados.");

        Owner owner = OwnerCoreMapper.of(command);
        return ownerGateway.create(owner);
    }
}
