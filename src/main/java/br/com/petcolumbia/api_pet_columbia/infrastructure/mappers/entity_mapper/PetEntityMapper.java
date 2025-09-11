package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.pet.Pet;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.PetEntity;

import java.util.List;

public class PetEntityMapper {

    public static Pet toDomain(PetEntity petEntity) {
        if (petEntity == null) {
            return null;
        }

        return new Pet(
                petEntity.getId(),
                OwnerEntityMapper.of(petEntity.getOwner()),
                petEntity.getName(),
                petEntity.getSize(),
                petEntity.getSpecies(),
                petEntity.getBreed(),
                petEntity.getCoat(),
                petEntity.getAge(),
                petEntity.getSex(),
                petEntity.getCreatedAt(),
                petEntity.getLastUpdate(),
                AppointmentEntityMapper.toDomainList(petEntity.getAppointments())
        );
    }

    public static List<Pet> toDomainList(List<PetEntity> petEntities) {
        if (petEntities == null) {
            return null;
        }

        return petEntities.stream().map(PetEntityMapper::toDomain).toList();
    }
}
