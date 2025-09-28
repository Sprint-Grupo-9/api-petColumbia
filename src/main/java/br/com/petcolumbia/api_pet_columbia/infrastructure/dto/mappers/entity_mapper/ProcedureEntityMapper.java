package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.procedure.Procedure;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.EmployeeProcedureAssociationEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.ProcedureEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProcedureEntityMapper {

    // Main mapping methods with context
    public static Procedure toDomain(ProcedureEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Procedure", entity.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.PROCEDURE_EMPLOYEES)) {
            return createProcedureWithoutRelations(entity, context);
        }

        context.markAsProcessed(entityKey);

        try {
            return new Procedure(
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_EMPLOYEES)
                        ? EmployeeProcedureAssociationEntityMapper.toDomainList(entity.getEmployeeProcedures(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_PRICES_DURATIONS)
                        ? ProcedurePriceAndDurationEntityMapper.toDomainList(entity.getPricesAndDurations(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static ProcedureEntity toEntity(Procedure procedure, MappingContext context) {
        if (procedure == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Procedure", procedure.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.PROCEDURE_EMPLOYEES)) {
            return createProcedureEntityWithoutRelations(procedure, context);
        }

        context.markAsProcessed(entityKey);

        try {
            return new ProcedureEntity(
                    procedure.getId(),
                    procedure.getName(),
                    procedure.getDescription(),
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_EMPLOYEES)
                        ? EmployeeProcedureAssociationEntityMapper.toEntityList(procedure.getEmployeeProcedures(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_PRICES_DURATIONS)
                        ? ProcedurePriceAndDurationEntityMapper.toEntityList(procedure.getPricesAndDurations(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static Procedure toDomain(ProcedureEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static ProcedureEntity toEntity(Procedure procedure) {
        return toEntity(procedure, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<Procedure> toDomainList(List<ProcedureEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<Procedure> toDomainList(List<ProcedureEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static List<ProcedureEntity> toEntityList(List<Procedure> procedures, MappingContext context) {
        if (procedures == null) {
            return null;
        }
        return procedures.stream()
                .map(procedure -> toEntity(procedure, context))
                .collect(Collectors.toList());
    }

    public static List<ProcedureEntity> toEntityList(List<Procedure> procedures) {
        return toEntityList(procedures, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static Procedure createProcedureWithoutRelations(ProcedureEntity entity, MappingContext context) {
        return new Procedure(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                null,
                context.shouldMapRelation(
                    MappingContext.createEntityKey("Procedure", entity.getId()),
                    RelationType.PROCEDURE_PRICES_DURATIONS)
                    ? ProcedurePriceAndDurationEntityMapper.toDomainList(entity.getPricesAndDurations(), context)
                    : null
        );
    }

    private static ProcedureEntity createProcedureEntityWithoutRelations(Procedure procedure, MappingContext context) {
        return new ProcedureEntity(
                procedure.getId(),
                procedure.getName(),
                procedure.getDescription(),
                null,
                context.shouldMapRelation(
                    MappingContext.createEntityKey("Procedure", procedure.getId()),
                    RelationType.PROCEDURE_PRICES_DURATIONS)
                    ? ProcedurePriceAndDurationEntityMapper.toEntityList(procedure.getPricesAndDurations(), context)
                    : null
        );
    }
}
