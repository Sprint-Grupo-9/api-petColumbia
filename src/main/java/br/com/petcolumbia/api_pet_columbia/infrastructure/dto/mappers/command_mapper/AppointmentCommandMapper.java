package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.command_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment.AppointmentUpdateDto;

public class AppointmentCommandMapper {

    public static AppointmentCreateCommand toCommand(AppointmentCreateDto dto) {
        return new AppointmentCreateCommand(
                dto.getPetId(),
                dto.getEmployee_id(),
                dto.getProceduresNames(),
                dto.getTotalPrice(),
                dto.getObservations(),
                dto.getTaxiService(),
                dto.getStartDateTime(),
                dto.getDurationMinutes()
        );
    }

    public static AppointmentUpdateCommand toCommand(AppointmentUpdateDto dto) {
        return new AppointmentUpdateCommand(
                dto.getPetId(),
                dto.getEmployee_id(),
                dto.getProcedures(),
                dto.getTotalPrice(),
                dto.getObservations(),
                dto.getTaxiService(),
                dto.getStartDateTime(),
                dto.getDurationMinutes()
        );
    }
}

