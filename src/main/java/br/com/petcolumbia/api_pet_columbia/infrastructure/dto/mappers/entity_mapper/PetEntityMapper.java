package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PetEntityMapper {

    // Main mapping methods with context
    public static Pet toDomain(PetEntity petEntity, MappingContext context) {
        if (petEntity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Pet", petEntity.getId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createPetWithoutRelations(petEntity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new Pet(
                    petEntity.getId(),
                    context.shouldIncludeRelation(RelationType.PET_OWNER)
                        ? OwnerEntityMapper.toDomain(petEntity.getOwner(), context)
                        : null,
                    petEntity.getName(),
                    petEntity.getSize(),
                    petEntity.getSpecies(),
                    petEntity.getBreed(),
                    petEntity.getCoat(),
                    petEntity.getAge(),
                    petEntity.getSex(),
                    petEntity.getCreatedAt(),
                    petEntity.getLastUpdate(),
                    context.shouldIncludeRelation(RelationType.PET_APPOINTMENTS)
                        ? AppointmentEntityMapper.toDomainList(petEntity.getAppointments(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static PetEntity toEntity(Pet pet, MappingContext context) {
        if (pet == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Pet", pet.getId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createPetEntityWithoutRelations(pet);
        }

        context.markAsProcessed(entityKey);

        try {
            return new PetEntity(
                    pet.getId(),
                    context.shouldIncludeRelation(RelationType.PET_OWNER)
                        ? OwnerEntityMapper.toEntity(pet.getOwner(), context)
                        : null,
                    pet.getName(),
                    pet.getSize(),
                    pet.getSpecies(),
                    pet.getBreed(),
                    pet.getCoat(),
                    pet.getAge(),
                    pet.getSex(),
                    pet.getCreatedAt(),
                    pet.getLastUpdate(),
                    context.shouldIncludeRelation(RelationType.PET_APPOINTMENTS)
                        ? AppointmentEntityMapper.toEntityList(pet.getAppointments(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static Pet toDomain(PetEntity petEntity) {
        return toDomain(petEntity, new MappingContext(new MappingStrategy.PetWithOwnerMapping()));
    }

    public static Pet toDomainWithoutOwner(PetEntity petEntity) {
        return toDomain(petEntity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static Pet toDomainWithoutAppointments(PetEntity petEntity) {
        return toDomain(petEntity, new MappingContext(new MappingStrategy.PetWithOwnerMapping()));
    }

    public static PetEntity toEntity(Pet pet) {
        return toEntity(pet, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static PetEntity toEntityWithoutOwner(Pet pet) {
        return toEntity(pet, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<Pet> toDomainList(List<PetEntity> petEntities, MappingContext context) {
        if (petEntities == null) {
            return null;
        }
        return petEntities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<Pet> toDomainList(List<PetEntity> petEntities) {
        return toDomainList(petEntities, new MappingContext(new MappingStrategy.PetWithOwnerMapping()));
    }

    public static List<PetEntity> toEntityList(List<Pet> pets, MappingContext context) {
        if (pets == null) {
            return null;
        }
        return pets.stream()
                .map(pet -> toEntity(pet, context))
                .collect(Collectors.toList());
    }

    public static List<PetEntity> toEntityList(List<Pet> pets) {
        return toEntityList(pets, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static Pet createPetWithoutRelations(PetEntity petEntity) {
        return new Pet(
                petEntity.getId(),
                null,
                petEntity.getName(),
                petEntity.getSize(),
                petEntity.getSpecies(),
                petEntity.getBreed(),
                petEntity.getCoat(),
                petEntity.getAge(),
                petEntity.getSex(),
                petEntity.getCreatedAt(),
                petEntity.getLastUpdate(),
                null
        );
    }

    private static PetEntity createPetEntityWithoutRelations(Pet pet) {
        return new PetEntity(
                pet.getId(),
                null,
                pet.getName(),
                pet.getSize(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getCoat(),
                pet.getAge(),
                pet.getSex(),
                pet.getCreatedAt(),
                pet.getLastUpdate(),
                null
        );
    }
}
