package br.com.petcolumbia.api_pet_columbia.core.adapter.appointment;

import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.BusyTimeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentGateway {
    List<Appointment> findAllAppointmentsByOwnerId(Integer ownerId);
    Appointment createAppointment(AppointmentCreateCommand command);
    Appointment updateAppointmentById(Integer id, AppointmentUpdateCommand command);
    void deleteAppointmentById(Integer id);
    List<BusyTimeResponseDto> findBusyTimesByEmployeeAndDate(Integer employeeId, LocalDate date);
    List<Appointment> findAppointmentsByDate(LocalDate date);
    boolean existsById(Integer id);
}

