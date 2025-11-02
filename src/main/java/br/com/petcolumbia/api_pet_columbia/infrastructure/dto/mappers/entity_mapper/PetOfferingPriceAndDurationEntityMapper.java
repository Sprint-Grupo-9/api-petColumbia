package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering_price_and_duration.PetOfferingPriceAndDuration;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingPriceAndDurationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PetOfferingPriceAndDurationEntityMapper {

    // Main mapping methods with context
    public static PetOfferingPriceAndDuration toDomain(PetOfferingPriceAndDurationEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("PetOfferingPriceAndDuration", entity.getId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createPriceAndDurationWithoutPetOffering(entity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new PetOfferingPriceAndDuration(
                    entity.getId(),
                    context.shouldIncludeRelation(RelationType.PET_OFFERING_PRICES_DURATIONS)
                        ? PetOfferingEntityMapper.toDomain(entity.getPetOffering(), context)
                        : null,
                    entity.getPetSize(),
                    entity.getPetCoat(),
                    entity.getPrice(),
                    entity.getDuration()
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static PetOfferingPriceAndDurationEntity toEntity(PetOfferingPriceAndDuration petOfferingPriceAndDuration, MappingContext context) {
        if (petOfferingPriceAndDuration == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("PetOfferingPriceAndDuration", petOfferingPriceAndDuration.getId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createEntityWithoutPetOffering(petOfferingPriceAndDuration);
        }

        context.markAsProcessed(entityKey);

        try {
            return new PetOfferingPriceAndDurationEntity(
                    petOfferingPriceAndDuration.getId(),
                    context.shouldIncludeRelation(RelationType.PET_OFFERING_PRICES_DURATIONS)
                        ? PetOfferingEntityMapper.toEntity(petOfferingPriceAndDuration.getPetOffering(), context)
                        : null,
                    petOfferingPriceAndDuration.getPetSize(),
                    petOfferingPriceAndDuration.getPetCoat(),
                    petOfferingPriceAndDuration.getPrice(),
                    petOfferingPriceAndDuration.getDuration()
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static PetOfferingPriceAndDuration toDomain(PetOfferingPriceAndDurationEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static PetOfferingPriceAndDurationEntity toEntity(PetOfferingPriceAndDuration petOfferingPriceAndDuration) {
        return toEntity(petOfferingPriceAndDuration, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<PetOfferingPriceAndDuration> toDomainList(List<PetOfferingPriceAndDurationEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<PetOfferingPriceAndDuration> toDomainList(List<PetOfferingPriceAndDurationEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static List<PetOfferingPriceAndDurationEntity> toEntityList(List<PetOfferingPriceAndDuration> petOfferingPriceAndDurations, MappingContext context) {
        if (petOfferingPriceAndDurations == null) {
            return null;
        }
        return petOfferingPriceAndDurations.stream()
                .map(item -> toEntity(item, context))
                .collect(Collectors.toList());
    }

    public static List<PetOfferingPriceAndDurationEntity> toEntityList(List<PetOfferingPriceAndDuration> petOfferingPriceAndDurations) {
        return toEntityList(petOfferingPriceAndDurations, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static PetOfferingPriceAndDuration createPriceAndDurationWithoutPetOffering(PetOfferingPriceAndDurationEntity entity) {
        return new PetOfferingPriceAndDuration(
                entity.getId(),
                null,
                entity.getPetSize(),
                entity.getPetCoat(),
                entity.getPrice(),
                entity.getDuration()
        );
    }

    private static PetOfferingPriceAndDurationEntity createEntityWithoutPetOffering(PetOfferingPriceAndDuration petOfferingPriceAndDuration) {
        return new PetOfferingPriceAndDurationEntity(
                petOfferingPriceAndDuration.getId(),
                null,
                petOfferingPriceAndDuration.getPetSize(),
                petOfferingPriceAndDuration.getPetCoat(),
                petOfferingPriceAndDuration.getPrice(),
                petOfferingPriceAndDuration.getDuration()
        );
    }
}