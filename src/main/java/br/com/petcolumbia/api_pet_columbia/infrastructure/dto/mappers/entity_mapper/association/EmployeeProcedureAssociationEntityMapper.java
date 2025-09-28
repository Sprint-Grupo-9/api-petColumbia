package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.EmployeeProcedureAssociation;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.EmployeeEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.ProcedureEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.helper.EmployeeProcedureAssociationIdMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.EmployeeProcedureAssociationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeProcedureAssociationEntityMapper {

    // Main mapping methods with context
    public static EmployeeProcedureAssociation toDomain(EmployeeProcedureAssociationEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("EmployeeProcedureAssociation", entity.getId().getEmployeeId());

        if (!context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_PROCEDURES)) {
            return createAssociationWithoutRelations(entity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new EmployeeProcedureAssociation(
                    EmployeeProcedureAssociationIdMapper.toDomain(entity.getId()),
                    context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_PROCEDURES)
                        ? EmployeeEntityMapper.toDomain(entity.getEmployee(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_EMPLOYEES)
                        ? ProcedureEntityMapper.toDomain(entity.getProcedure(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static EmployeeProcedureAssociationEntity toEntity(EmployeeProcedureAssociation association, MappingContext context) {
        if (association == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("EmployeeProcedureAssociation", association.getId().getEmployeeId());

        if (!context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_PROCEDURES)) {
            return createEntityWithoutRelations(association);
        }

        context.markAsProcessed(entityKey);

        try {
            return new EmployeeProcedureAssociationEntity(
                    EmployeeProcedureAssociationIdMapper.toEntity(association.getId()),
                    context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_PROCEDURES)
                        ? EmployeeEntityMapper.toEntity(association.getEmployee(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.PROCEDURE_EMPLOYEES)
                        ? ProcedureEntityMapper.toEntity(association.getProcedure(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static EmployeeProcedureAssociation toDomain(EmployeeProcedureAssociationEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static EmployeeProcedureAssociationEntity toEntity(EmployeeProcedureAssociation association) {
        return toEntity(association, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<EmployeeProcedureAssociation> toDomainList(List<EmployeeProcedureAssociationEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<EmployeeProcedureAssociation> toDomainList(List<EmployeeProcedureAssociationEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static List<EmployeeProcedureAssociationEntity> toEntityList(List<EmployeeProcedureAssociation> associations, MappingContext context) {
        if (associations == null) {
            return null;
        }
        return associations.stream()
                .map(association -> toEntity(association, context))
                .collect(Collectors.toList());
    }

    public static List<EmployeeProcedureAssociationEntity> toEntityList(List<EmployeeProcedureAssociation> associations) {
        return toEntityList(associations, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static EmployeeProcedureAssociation createAssociationWithoutRelations(EmployeeProcedureAssociationEntity entity) {
        return new EmployeeProcedureAssociation(
                EmployeeProcedureAssociationIdMapper.toDomain(entity.getId()),
                null,
                null
        );
    }

    private static EmployeeProcedureAssociationEntity createEntityWithoutRelations(EmployeeProcedureAssociation association) {
        return new EmployeeProcedureAssociationEntity(
                EmployeeProcedureAssociationIdMapper.toEntity(association.getId()),
                null,
                null
        );
    }
}