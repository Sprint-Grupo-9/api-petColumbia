package br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

public class CreateAppointmentUseCase {

    private final AppointmentGateway appointmentGateway;

    public CreateAppointmentUseCase(AppointmentGateway appointmentGateway) {
        this.appointmentGateway = appointmentGateway;
    }

    public Appointment execute(AppointmentCreateCommand command) {
        return appointmentGateway.createAppointment(command);
    }
}

