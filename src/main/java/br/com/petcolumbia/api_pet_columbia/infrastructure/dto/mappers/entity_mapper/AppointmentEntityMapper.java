package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.RelationType;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.AppointmentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentEntityMapper {

    // Main mapping methods with context
    public static Appointment toDomain(AppointmentEntity entity, MappingContext context) {
        if (entity == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Appointment", entity.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.APPOINTMENT_PET)) {
            return createAppointmentWithoutRelations(entity);
        }

        context.markAsProcessed(entityKey);

        try {
            return new Appointment(
                    entity.getId(),
                    context.shouldMapRelation(entityKey, RelationType.APPOINTMENT_PET)
                        ? PetEntityMapper.toDomain(entity.getPet(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.APPOINTMENT_EMPLOYEE)
                        ? EmployeeEntityMapper.toDomain(entity.getEmployee(), context)
                        : null,
                    entity.getObservations(),
                    entity.getTaxiService(),
                    entity.getProcedures(),
                    entity.getTotalPrice(),
                    entity.getStartDateTime(),
                    entity.getEndDateTime(),
                    entity.getFinished(),
                    entity.getCreatedAt(),
                    entity.getLastUpdate()
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    public static AppointmentEntity toEntity(Appointment appointment, MappingContext context) {
        if (appointment == null) {
            return null;
        }

        String entityKey = MappingContext.createEntityKey("Appointment", appointment.getId());

        if (!context.shouldMapRelation(entityKey, RelationType.APPOINTMENT_PET)) {
            return createAppointmentEntityWithoutRelations(appointment);
        }

        context.markAsProcessed(entityKey);

        try {
            return new AppointmentEntity(
                    appointment.getId(),
                    context.shouldMapRelation(entityKey, RelationType.APPOINTMENT_PET)
                        ? PetEntityMapper.toEntity(appointment.getPet(), context)
                        : null,
                    context.shouldMapRelation(entityKey, RelationType.APPOINTMENT_EMPLOYEE)
                        ? EmployeeEntityMapper.toEntity(appointment.getEmployee(), context)
                        : null,
                    appointment.getObservations(),
                    appointment.getTaxiService(),
                    appointment.getServices(),
                    appointment.getTotalPrice(),
                    appointment.getStartDateTime(),
                    appointment.getEndDateTime(),
                    appointment.getFinished(),
                    appointment.getCreatedAt(),
                    appointment.getLastUpdate()
            );
        } finally {
            context.unmarkAsProcessed(entityKey);
        }
    }

    // Convenience methods for backward compatibility
    public static Appointment toDomain(AppointmentEntity entity) {
        return toDomain(entity, new MappingContext(new MappingStrategy.AppointmentFullMapping()));
    }

    public static AppointmentEntity toEntity(Appointment appointment) {
        return toEntity(appointment, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // List mapping methods
    public static List<Appointment> toDomainList(List<AppointmentEntity> entities, MappingContext context) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(entity -> toDomain(entity, context))
                .collect(Collectors.toList());
    }

    public static List<Appointment> toDomainList(List<AppointmentEntity> entities) {
        return toDomainList(entities, new MappingContext(new MappingStrategy.AppointmentFullMapping()));
    }

    public static List<AppointmentEntity> toEntityList(List<Appointment> appointments, MappingContext context) {
        if (appointments == null) {
            return null;
        }

        return appointments.stream()
                .map(appointment -> toEntity(appointment, context))
                .collect(Collectors.toList());
    }

    public static List<AppointmentEntity> toEntityList(List<Appointment> appointments) {
        return toEntityList(appointments, new MappingContext(new MappingStrategy.ShallowMapping()));
    }

    // Helper methods
    private static Appointment createAppointmentWithoutRelations(AppointmentEntity entity) {
        return new Appointment(
                entity.getId(),
                null,
                null,
                entity.getObservations(),
                entity.getTaxiService(),
                entity.getProcedures(),
                entity.getTotalPrice(),
                entity.getStartDateTime(),
                entity.getEndDateTime(),
                entity.getFinished(),
                entity.getCreatedAt(),
                entity.getLastUpdate()
        );
    }

    private static AppointmentEntity createAppointmentEntityWithoutRelations(Appointment appointment) {
        return new AppointmentEntity(
                appointment.getId(),
                null,
                null,
                appointment.getObservations(),
                appointment.getTaxiService(),
                appointment.getServices(),
                appointment.getTotalPrice(),
                appointment.getStartDateTime(),
                appointment.getEndDateTime(),
                appointment.getFinished(),
                appointment.getCreatedAt(),
                appointment.getLastUpdate()
        );
    }
}