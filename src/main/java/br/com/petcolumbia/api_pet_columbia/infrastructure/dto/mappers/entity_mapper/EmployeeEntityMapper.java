package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.EmployeePetOfferingAssociationEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeEntityMapper {

    // Main mapping methods with context
    public static Employee toDomain(EmployeeEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Employee", entity.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_APPOINTMENTS)) {
            return createEmployeeWithoutRelations(entity, context);
        }

        context.markAsProcessed(entityKey);

        try {
            return new Employee(
                    entity.getId(),
                    entity.getName(),
                    context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_APPOINTMENTS)
                        ? AppointmentEntityMapper.toDomainList(entity.getAppointments(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_PET_OFFERINGS)
                        ? EmployeePetOfferingAssociationEntityMapper.toDomainList(entity.getEmployeePetOfferings(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static EmployeeEntity toEntity(Employee employee, MappingContext context) {
        if (employee == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Employee", employee.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_APPOINTMENTS)) {
            return createEmployeeEntityWithoutRelations(employee, context);
        }

        context.markAsProcessed(entityKey);

        try {
            return new EmployeeEntity(
                    employee.getId(),
                    employee.getName(),
                    context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_APPOINTMENTS)
                        ? AppointmentEntityMapper.toEntityList(employee.getAppointments(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.EMPLOYEE_PET_OFFERINGS)
                        ? EmployeePetOfferingAssociationEntityMapper.toEntityList(employee.getEmployeePetOfferings(), context)
                        : null
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static Employee toDomain(EmployeeEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.EmployeeWithPetOfferingsMapping()));
    }

    public static EmployeeEntity toEntity(Employee employee) {
        return toEntity(employee, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<Employee> toDomainList(List<EmployeeEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<Employee> toDomainList(List<EmployeeEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.EmployeeWithPetOfferingsMapping()));
    }

    public static List<EmployeeEntity> toEntityList(List<Employee> employees, MappingContext context) {
        if (employees == null) {
            return null;
        }
        return employees.stream()
                .map(employee -> toEntity(employee, context))
                .collect(Collectors.toList());
    }

    public static List<EmployeeEntity> toEntityList(List<Employee> employees) {
        return toEntityList(employees, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static Employee createEmployeeWithoutRelations(EmployeeEntity entity, MappingContext context) {
        return new Employee(
                entity.getId(),
                entity.getName(),
                null,
                context.shouldMapRelation(
                    MappingContext.createEntityKey("Employee", entity.getId()),
                    RelationType.EMPLOYEE_PET_OFFERINGS)
                    ? EmployeePetOfferingAssociationEntityMapper.toDomainList(entity.getEmployeePetOfferings(), context)
                    : null
        );
    }

    private static EmployeeEntity createEmployeeEntityWithoutRelations(Employee employee, MappingContext context) {
        return new EmployeeEntity(
                employee.getId(),
                employee.getName(),
                null,
                context.shouldMapRelation(
                    MappingContext.createEntityKey("Employee", employee.getId()),
                    RelationType.EMPLOYEE_PET_OFFERINGS)
                    ? EmployeePetOfferingAssociationEntityMapper.toEntityList(employee.getEmployeePetOfferings(), context)
                    : null
        );
    }
}