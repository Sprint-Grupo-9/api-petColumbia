package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.EmployeePetOfferingAssociation;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.EmployeeEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.PetOfferingEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.helper.EmployeePetOfferingAssociationIdMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.EmployeePetOfferingAssociationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeePetOfferingAssociationEntityMapper {

    // Main mapping methods with context
    public static EmployeePetOfferingAssociation toDomain(EmployeePetOfferingAssociationEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("EmployeePetOfferingAssociation", entity.getId().getEmployeeId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createAssociationWithoutRelations(entity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new EmployeePetOfferingAssociation(
                    EmployeePetOfferingAssociationIdMapper.toDomain(entity.getId()),
                    context.shouldIncludeRelation(RelationType.EMPLOYEE_PET_OFFERINGS)
                        ? EmployeeEntityMapper.toDomain(entity.getEmployee(), context)
                        : null,
                    context.shouldIncludeRelation(RelationType.PET_OFFERING_EMPLOYEES)
                        ? PetOfferingEntityMapper.toDomain(entity.getPetOffering(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static EmployeePetOfferingAssociationEntity toEntity(EmployeePetOfferingAssociation association, MappingContext context) {
        if (association == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("EmployeePetOfferingAssociation", association.getId().getEmployeeId());

        // Check if already being processed (circular reference prevention)
        if (context.isBeingProcessed(entityKey)) {
            return createEntityWithoutRelations(association);
        }

        context.markAsProcessed(entityKey);

        try {
            return new EmployeePetOfferingAssociationEntity(
                    EmployeePetOfferingAssociationIdMapper.toEntity(association.getId()),
                    context.shouldIncludeRelation(RelationType.EMPLOYEE_PET_OFFERINGS)
                        ? EmployeeEntityMapper.toEntity(association.getEmployee(), context)
                        : null,
                    context.shouldIncludeRelation(RelationType.PET_OFFERING_EMPLOYEES)
                        ? PetOfferingEntityMapper.toEntity(association.getPetOffering(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static EmployeePetOfferingAssociation toDomain(EmployeePetOfferingAssociationEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static EmployeePetOfferingAssociationEntity toEntity(EmployeePetOfferingAssociation association) {
        return toEntity(association, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<EmployeePetOfferingAssociation> toDomainList(List<EmployeePetOfferingAssociationEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<EmployeePetOfferingAssociation> toDomainList(List<EmployeePetOfferingAssociationEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    public static List<EmployeePetOfferingAssociationEntity> toEntityList(List<EmployeePetOfferingAssociation> associations, MappingContext context) {
        if (associations == null) {
            return null;
        }
        return associations.stream()
                .map(association -> toEntity(association, context))
                .collect(Collectors.toList());
    }

    public static List<EmployeePetOfferingAssociationEntity> toEntityList(List<EmployeePetOfferingAssociation> associations) {
        return toEntityList(associations, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static EmployeePetOfferingAssociation createAssociationWithoutRelations(EmployeePetOfferingAssociationEntity entity) {
        return new EmployeePetOfferingAssociation(
                EmployeePetOfferingAssociationIdMapper.toDomain(entity.getId()),
                null,
                null
        );
    }

    private static EmployeePetOfferingAssociationEntity createEntityWithoutRelations(EmployeePetOfferingAssociation association) {
        return new EmployeePetOfferingAssociationEntity(
                EmployeePetOfferingAssociationIdMapper.toEntity(association.getId()),
                null,
                null
        );
    }
}