package br.com.petcolumbia.api_pet_columbia.core.application.dto.mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;

public class OwnerCoreMapper {

    public static Owner of(OwnerCreateCommand command){
        return new Owner(
                null,
                command.name(),
                new PersonalInfo(
                        command.cpf(),
                        command.phoneNumber()
                ),
                command.email(),
                command.password(),
                new Address(
                        command.cep(),
                        command.neighborhood(),
                        command.street(),
                        command.number(),
                        command.complement()
                ),
                false,
                null
        );
    }
}
