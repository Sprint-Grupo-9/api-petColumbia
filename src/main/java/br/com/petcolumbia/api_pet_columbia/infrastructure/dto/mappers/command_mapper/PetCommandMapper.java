package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.command_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet.PetCreateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet.PetUpdateDto;

public class PetCommandMapper {

    public static PetCreateCommand of(PetCreateDto dto) {
        return new PetCreateCommand(
                dto.getName(),
                dto.getSize(),
                dto.getSpecies(),
                dto.getBreed(),
                dto.getCoat(),
                dto.getAge(),
                dto.getSex()
        );
    }

    public static PetUpdateCommand of(PetUpdateDto dto) {
        return new PetUpdateCommand(
                dto.getName(),
                dto.getSize(),
                dto.getSpecies(),
                dto.getBreed(),
                dto.getCoat(),
                dto.getAge(),
                dto.getSex()
        );
    }
}

