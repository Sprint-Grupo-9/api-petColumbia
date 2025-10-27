package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.EmployeePetOfferingAssociationEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PetOfferingEntityMapper {

    // Main mapping methods with context
    public static PetOffering toDomain(PetOfferingEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("PetOffering", entity.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.PET_OFFERING_EMPLOYEES)) {
            return createPetOfferingWithoutRelations(entity, context);
        }

        context.markAsProcessed(entityKey);

        try {
            return new PetOffering(
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    context.shouldMapRelation(entityKey, RelationType.PET_OFFERING_EMPLOYEES)
                        ? EmployeePetOfferingAssociationEntityMapper.toDomainList(entity.getEmployeePetOfferings(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.PET_OFFERING_PRICES_DURATIONS)
                        ? PetOfferingPriceAndDurationEntityMapper.toDomainList(entity.getPricesAndDurations(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static PetOfferingEntity toEntity(PetOffering petOffering, MappingContext context) {
        if (petOffering == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("PetOffering", petOffering.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.PET_OFFERING_EMPLOYEES)) {
            return createPetOfferingEntityWithoutRelations(petOffering, context);
        }

        context.markAsProcessed(entityKey);

        try {
            return new PetOfferingEntity(
                    petOffering.getId(),
                    petOffering.getName(),
                    petOffering.getDescription(),
                    context.shouldMapRelation(entityKey, RelationType.PET_OFFERING_EMPLOYEES)
                        ? EmployeePetOfferingAssociationEntityMapper.toEntityList(petOffering.getEmployeePetOfferings(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.PET_OFFERING_PRICES_DURATIONS)
                        ? PetOfferingPriceAndDurationEntityMapper.toEntityList(petOffering.getPricesAndDurations(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static PetOffering toDomain(PetOfferingEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static PetOfferingEntity toEntity(PetOffering petOffering) {
        return toEntity(petOffering, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<PetOffering> toDomainList(List<PetOfferingEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<PetOffering> toDomainList(List<PetOfferingEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static List<PetOfferingEntity> toEntityList(List<PetOffering> petOfferings, MappingContext context) {
        if (petOfferings == null) {
            return null;
        }
        return petOfferings.stream()
                .map(petOffering -> toEntity(petOffering, context))
                .collect(Collectors.toList());
    }

    public static List<PetOfferingEntity> toEntityList(List<PetOffering> petOfferings) {
        return toEntityList(petOfferings, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static PetOffering createPetOfferingWithoutRelations(PetOfferingEntity entity, MappingContext context) {
        return new PetOffering(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                null,
                context.shouldMapRelation(
                    MappingContext.createEntityKey("PetOffering", entity.getId()),
                    RelationType.PET_OFFERING_PRICES_DURATIONS)
                    ? PetOfferingPriceAndDurationEntityMapper.toDomainList(entity.getPricesAndDurations(), context)
                    : null
        );
    }

    private static PetOfferingEntity createPetOfferingEntityWithoutRelations(PetOffering petOffering, MappingContext context) {
        return new PetOfferingEntity(
                petOffering.getId(),
                petOffering.getName(),
                petOffering.getDescription(),
                null,
                context.shouldMapRelation(
                    MappingContext.createEntityKey("PetOffering", petOffering.getId()),
                    RelationType.PET_OFFERING_PRICES_DURATIONS)
                    ? PetOfferingPriceAndDurationEntityMapper.toEntityList(petOffering.getPricesAndDurations(), context)
                    : null
        );
    }
}
