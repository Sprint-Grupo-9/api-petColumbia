package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentResponseMapper {

    public static AppointmentResponseDto toResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(appointment.getId());

        if (appointment.getPet() != null) {
            dto.setPet(PetResponseMapper.toResponse(appointment.getPet()));
        }

        if (appointment.getEmployee() != null) {
            dto.setEmployee(EmployeeResponseMapper.toResponse(appointment.getEmployee()));
        }

        dto.setObservations(appointment.getObservations());
        dto.setTaxiService(appointment.getTaxiService());
        dto.setPetOfferingNames(appointment.getPetOfferingNames());
        dto.setTotalPrice(appointment.getTotalPrice());
        dto.setStartDateTime(appointment.getStartDateTime());
        dto.setEndDateTime(appointment.getEndDateTime());

        return dto;
    }

    public static List<AppointmentResponseDto> toResponseList(List<Appointment> appointments) {
        if (appointments == null) {
            return null;
        }

        return appointments.stream()
                .map(AppointmentResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}

