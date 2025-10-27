package br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

import java.time.LocalDate;
import java.util.List;

public class ListAppointmentsByDateUseCase {

    private final AppointmentGateway appointmentGateway;

    public ListAppointmentsByDateUseCase(AppointmentGateway appointmentGateway) {
        this.appointmentGateway = appointmentGateway;
    }

    public List<Appointment> execute(LocalDate date) {
        return appointmentGateway.findAppointmentsByDate(date);
    }
}

