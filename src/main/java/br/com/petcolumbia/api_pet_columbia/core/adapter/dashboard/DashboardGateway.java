package br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.*;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DashboardGateway {
    List<Appointment> findAppointmentsByDate(LocalDate date);
    LastPetAndOwnerAppointmentsResponseDto getLastAppointmentsOfPetAndOwner(List<Appointment> appointments);
    Map<LocalDate, Long> getAmountProceduresCountPerDay();
    TopServiceResponseDto getMostPerformedProcedureLastThirtyDays();
    LeastServiceResponseDto getLeastPerformedProcedureLastThirtyDays();
    TopProceduresTimingResponse getMostProceduresTimingLastThirtyDays();
    LeastProceduresTimingResponse getLeastProceduresTimingLastThirtyDays();
}

