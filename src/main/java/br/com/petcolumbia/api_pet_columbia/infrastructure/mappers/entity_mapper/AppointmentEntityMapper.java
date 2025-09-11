package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.AppointmentEntity;

import java.util.List;

public class AppointmentEntityMapper {

    public static Appointment toDomain(AppointmentEntity entity) {
        if (entity == null)
            return null;

        return new Appointment(
                entity.getId(),
                PetEntityMapper.toDomain(entity.getPet()),
                EmployeeEntityMapper.toDomain(entity.getEmployee()),
                entity.getObservations(),
                entity.getTaxiService(),
                entity.getServices(),
                entity.getTotalPrice(),
                entity.getStartDateTime(),
                entity.getEndDateTime(),
                entity.getFinished(),
                entity.getCreatedAt(),
                entity.getLastUpdate()
        );
    }

    public static List<Appointment> toDomainList(List<AppointmentEntity> entities) {
        if (entities == null)
            return null;

        return entities.stream().map(AppointmentEntityMapper::toDomain).toList();
    }
}
