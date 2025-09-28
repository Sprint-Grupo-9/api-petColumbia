package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.procedure_price_and_duration.ProcedurePriceAndDuration;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.ProcedurePriceAndDurationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProcedurePriceAndDurationEntityMapper {

    // Main mapping methods with context
    public static ProcedurePriceAndDuration toDomain(ProcedurePriceAndDurationEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("ProcedurePriceAndDuration", entity.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.PROCEDURE_PRICES_DURATIONS)) {
            return createPriceAndDurationWithoutProcedure(entity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new ProcedurePriceAndDuration(
                    entity.getId(),
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_PRICES_DURATIONS)
                        ? ProcedureEntityMapper.toDomain(entity.getProcedure(), context)
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

    public static ProcedurePriceAndDurationEntity toEntity(ProcedurePriceAndDuration procedurePriceAndDuration, MappingContext context) {
        if (procedurePriceAndDuration == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("ProcedurePriceAndDuration", procedurePriceAndDuration.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.PROCEDURE_PRICES_DURATIONS)) {
            return createEntityWithoutProcedure(procedurePriceAndDuration);
        }

        context.markAsProcessed(entityKey);

        try {
            return new ProcedurePriceAndDurationEntity(
                    procedurePriceAndDuration.getId(),
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_PRICES_DURATIONS)
                        ? ProcedureEntityMapper.toEntity(procedurePriceAndDuration.getProcedure(), context)
                        : null,
                    procedurePriceAndDuration.getPetSize(),
                    procedurePriceAndDuration.getPetCoat(),
                    procedurePriceAndDuration.getPrice(),
                    procedurePriceAndDuration.getDuration()
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static ProcedurePriceAndDuration toDomain(ProcedurePriceAndDurationEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static ProcedurePriceAndDurationEntity toEntity(ProcedurePriceAndDuration procedurePriceAndDuration) {
        return toEntity(procedurePriceAndDuration, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<ProcedurePriceAndDuration> toDomainList(List<ProcedurePriceAndDurationEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<ProcedurePriceAndDuration> toDomainList(List<ProcedurePriceAndDurationEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static List<ProcedurePriceAndDurationEntity> toEntityList(List<ProcedurePriceAndDuration> procedurePriceAndDurations, MappingContext context) {
        if (procedurePriceAndDurations == null) {
            return null;
        }
        return procedurePriceAndDurations.stream()
                .map(item -> toEntity(item, context))
                .collect(Collectors.toList());
    }

    public static List<ProcedurePriceAndDurationEntity> toEntityList(List<ProcedurePriceAndDuration> procedurePriceAndDurations) {
        return toEntityList(procedurePriceAndDurations, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static ProcedurePriceAndDuration createPriceAndDurationWithoutProcedure(ProcedurePriceAndDurationEntity entity) {
        return new ProcedurePriceAndDuration(
                entity.getId(),
                null,
                entity.getPetSize(),
                entity.getPetCoat(),
                entity.getPrice(),
                entity.getDuration()
        );
    }

    private static ProcedurePriceAndDurationEntity createEntityWithoutProcedure(ProcedurePriceAndDuration procedurePriceAndDuration) {
        return new ProcedurePriceAndDurationEntity(
                procedurePriceAndDuration.getId(),
                null,
                procedurePriceAndDuration.getPetSize(),
                procedurePriceAndDuration.getPetCoat(),
                procedurePriceAndDuration.getPrice(),
                procedurePriceAndDuration.getDuration()
        );
    }
}