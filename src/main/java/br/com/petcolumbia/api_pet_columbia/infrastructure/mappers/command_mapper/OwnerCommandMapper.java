package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.command_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.Adress;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.ownerDtos.OwnerCreateDto;

public class OwnerCommandMapper {

    public static OwnerCreateCommand of(OwnerCreateDto ownerCreateDto){
        return new OwnerCreateCommand(
                ownerCreateDto.getName(),
                ownerCreateDto.getCpf(),
                ownerCreateDto.getPhoneNumber(),
                ownerCreateDto.getEmail(),
                ownerCreateDto.getPassword(),
                ownerCreateDto.getCep(),
                ownerCreateDto.getNeighborhood(),
                ownerCreateDto.getStreet(),
                ownerCreateDto.getNumber(),
                ownerCreateDto.getComplement()
        );
    }

    public static Owner of(OwnerCreateCommand command){
        return new Owner(
                null,
                command.name(),
                new PersonalInfo(command.cpf(), command.phoneNumber()),
                command.email(),
                command.password(),
                new Adress(
                        command.cpf(), command.neighborhood(), command.street(), command.number(), command.complement()),
                false,
                null
        );
    }
}
