package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentCardInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastAppointmentsListDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastPetAndOwnerAppointmentsResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDashboardMapper {

    public static AppointmentCardInfoResponseDto toCardInfoResponse(Appointment appointment) {
        if (appointment == null || appointment.getPet() == null || appointment.getPet().getOwner() == null) {
            throw new IllegalArgumentException("Appointment, Pet or Owner cannot be null");
        }

        AppointmentCardInfoResponseDto dto = new AppointmentCardInfoResponseDto();

        dto.setStartTime(appointment.getStartDateTime().toLocalTime());
        dto.setEndTime(appointment.getEndDateTime().toLocalTime());
        dto.setOwnerName(appointment.getPet().getOwner().getName());
        dto.setPetName(appointment.getPet().getName());
        dto.setPetBreed(appointment.getPet().getBreed());
        dto.setPetOfferingNames(appointment.getPetOfferingNames());
        dto.setPrice(appointment.getTotalPrice());
        dto.setTaxiService(appointment.getTaxiService());

        return dto;
    }

    public static List<AppointmentCardInfoResponseDto> toCardInfoResponseList(List<Appointment> appointments) {
        List<AppointmentCardInfoResponseDto> responseDtos = new ArrayList<>();

        for (Appointment appointment : appointments) {
            responseDtos.add(toCardInfoResponse(appointment));
        }

        return responseDtos;
    }

    public static AppointmentInfoResponseDto toInfoResponse(
            Appointment appointment,
            LastAppointmentsListDto lastPetAppointments,
            LastAppointmentsListDto lastOwnerAppointments
    ) {
        AppointmentInfoResponseDto dto = new AppointmentInfoResponseDto();

        dto.setId(appointment.getId());
        dto.setPet(PetResponseMapper.toOwnerInfoResponse(appointment.getPet()));
        dto.setEmployee(EmployeeResponseMapper.toResponse(appointment.getEmployee()));
        dto.setPetOfferingNames(appointment.getPetOfferingNames());
        dto.setTotalPrice(appointment.getTotalPrice());
        dto.setObservations(appointment.getObservations());
        dto.setTaxiService(appointment.getTaxiService());
        dto.setStartDateTime(appointment.getStartDateTime());
        dto.setEndDateTime(appointment.getEndDateTime());
        dto.setLastPetAppointments(lastPetAppointments);
        dto.setLastOwnerAppointments(lastOwnerAppointments);

        return dto;
    }

    public static List<AppointmentInfoResponseDto> toInfoResponseList(
            List<Appointment> appointments,
            LastPetAndOwnerAppointmentsResponseDto lastAppointments
    ) {
        List<AppointmentInfoResponseDto> responseDtos = new ArrayList<>();
        List<LastAppointmentsListDto> lastPetAppointments = lastAppointments.getLastPetAppointments();
        List<LastAppointmentsListDto> lastOwnerAppointments = lastAppointments.getLastOwnerAppointments();

        for (int i = 0; i < appointments.size(); i++) {
            responseDtos.add(toInfoResponse(
                    appointments.get(i),
                    lastPetAppointments.get(i),
                    lastOwnerAppointments.get(i)
            ));
        }

        return responseDtos;
    }
}

