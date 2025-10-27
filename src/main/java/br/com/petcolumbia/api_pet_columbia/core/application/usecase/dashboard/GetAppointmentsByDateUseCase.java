package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentCardInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.AppointmentsDashboardInfosResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastPetAndOwnerAppointmentsResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.AppointmentDashboardMapper;

import java.time.LocalDate;
import java.util.List;

public class GetAppointmentsByDateUseCase {

    private final DashboardGateway dashboardGateway;

    public GetAppointmentsByDateUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public AppointmentsDashboardInfosResponseDto execute(LocalDate date) {
        List<Appointment> appointments = dashboardGateway.findAppointmentsByDate(date);

        if (appointments.isEmpty()) {
            return null;
        }

        LastPetAndOwnerAppointmentsResponseDto lastAppointments =
                dashboardGateway.getLastAppointmentsOfPetAndOwner(appointments);

        AppointmentsDashboardInfosResponseDto response = new AppointmentsDashboardInfosResponseDto();

        List<AppointmentCardInfoResponseDto> cardResponses =
                AppointmentDashboardMapper.toCardInfoResponseList(appointments);

        List<AppointmentInfoResponseDto> infoResponses =
                AppointmentDashboardMapper.toInfoResponseList(appointments, lastAppointments);

        response.setCardResponses(cardResponses);
        response.setInfoResponses(infoResponses);

        return response;
    }
}

