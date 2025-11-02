package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerEntityMapper {

    // Main mapping methods with context
    public static Owner toDomain(OwnerEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Owner", entity.getId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createOwnerWithoutPets(entity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new Owner(
                    entity.getId(),
                    entity.getName(),
                    new PersonalInfo(entity.getCpf(), entity.getPhoneNumber()),
                    entity.getEmail(),
                    entity.getPassword(),
                    createAddress(entity),
                    entity.getAdm(),
                    context.shouldIncludeRelation(RelationType.OWNER_PETS)
                        ? PetEntityMapper.toDomainList(entity.getPets(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static OwnerEntity toEntity(Owner owner, MappingContext context) {
        if (owner == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Owner", owner.getId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createOwnerEntityWithoutPets(owner);
        }

        context.markAsProcessed(entityKey);

        try {
            return new OwnerEntity(
                    owner.getId(),
                    owner.getName(),
                    owner.getPersonalInfo().getCpf(),
                    owner.getPersonalInfo().getPhoneNumber(),
                    owner.getEmail(),
                    owner.getPassword(),
                    owner.getAdress().getCep(),
                    owner.getAdress().getNeighborhood(),
                    owner.getAdress().getStreet(),
                    owner.getAdress().getNumber(),
                    owner.getAdress().getComplement(),
                    owner.getAdm(),
                    null,
                    null,
                    context.shouldIncludeRelation(RelationType.OWNER_PETS)
                        ? PetEntityMapper.toEntityList(owner.getPets(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static Owner of(OwnerEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.OwnerWithPetsMapping()));
    }

    public static Owner ofWithoutPets(OwnerEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static OwnerEntity toEntity(Owner owner) {
        return toEntity(owner, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static OwnerEntity toEntityWithoutPets(Owner owner) {
        return toEntity(owner, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<Owner> toDomainList(List<OwnerEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<OwnerEntity> toEntityList(List<Owner> owners, MappingContext context) {
        if (owners == null) {
            return null;
        }
        return owners.stream()
                .map(owner -> toEntity(owner, context))
                .collect(Collectors.toList());
    }

    // Helper methods
    private static Owner createOwnerWithoutPets(OwnerEntity entity) {
        return new Owner(
                entity.getId(),
                entity.getName(),
                new PersonalInfo(entity.getCpf(), entity.getPhoneNumber()),
                entity.getEmail(),
                entity.getPassword(),
                createAddress(entity),
                entity.getAdm(),
                null
        );
    }

    private static OwnerEntity createOwnerEntityWithoutPets(Owner owner) {
        return new OwnerEntity(
                owner.getId(),
                owner.getName(),
                owner.getPersonalInfo().getCpf(),
                owner.getPersonalInfo().getPhoneNumber(),
                owner.getEmail(),
                owner.getPassword(),
                owner.getAdress().getCep(),
                owner.getAdress().getNeighborhood(),
                owner.getAdress().getStreet(),
                owner.getAdress().getNumber(),
                owner.getAdress().getComplement(),
                owner.getAdm(),
                null,
                null,
                null
        );
    }

    private static Address createAddress(OwnerEntity entity) {
        return new Address(
                entity.getCep(),
                entity.getNeighborhood(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement()
        );
    }
}
