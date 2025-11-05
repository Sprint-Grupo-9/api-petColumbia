package br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

public class UpdateAppointmentByIdUseCase {

    private final AppointmentGateway appointmentGateway;

    public UpdateAppointmentByIdUseCase(AppointmentGateway appointmentGateway) {
        this.appointmentGateway = appointmentGateway;
    }

    public Appointment execute(Integer id, AppointmentUpdateCommand command) {
        return appointmentGateway.updateAppointmentById(id, command);
    }
}

