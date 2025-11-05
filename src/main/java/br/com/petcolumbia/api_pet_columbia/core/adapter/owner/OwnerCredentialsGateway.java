package br.com.petcolumbia.api_pet_columbia.core.adapter.owner;

import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;

public interface OwnerCredentialsGateway {
    Owner updatePasswordById(Integer id, OwnerUpdatePasswordCommand command);
}
