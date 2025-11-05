package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.command_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerAutenticateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerUpdatePasswordDto;

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
                new Address(
                        command.cpf(), command.neighborhood(), command.street(), command.number(), command.complement()),
                false,
                null
        );
    }

    public static OwnerUpdateCommand of(OwnerUpdateDto dto){
        return new OwnerUpdateCommand(
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getCep(),
                dto.getNeighborhood(),
                dto.getStreet(),
                dto.getNumber(),
                dto.getComplement()
        );
    }

    public static OwnerUpdatePasswordCommand of(OwnerUpdatePasswordDto dto){
        return new OwnerUpdatePasswordCommand(
                dto.getNewPassword(),
                dto.getCurrentPassword()
        );
    }

    public static OwnerAutenticateCommand of(OwnerLoginDto dto){
        return new OwnerAutenticateCommand(
                dto.getEmail(),
                dto.getPassword()
        );
    }
}
