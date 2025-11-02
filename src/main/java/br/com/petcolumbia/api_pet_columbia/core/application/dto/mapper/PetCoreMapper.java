package br.com.petcolumbia.api_pet_columbia.core.application.dto.mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;

import java.time.LocalDateTime;

public class PetCoreMapper {

    public static Pet of(PetCreateCommand command) {
        return new Pet(
                null,
                null, // owner será setado no adapter
                command.name(),
                command.size(),
                command.species(),
                command.breed(),
                command.coat(),
                command.age(),
                command.sex(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }
}

