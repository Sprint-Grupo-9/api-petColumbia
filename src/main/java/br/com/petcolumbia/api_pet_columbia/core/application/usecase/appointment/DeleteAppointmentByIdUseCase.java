package br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;

public class DeleteAppointmentByIdUseCase {

    private final AppointmentGateway appointmentGateway;

    public DeleteAppointmentByIdUseCase(AppointmentGateway appointmentGateway) {
        this.appointmentGateway = appointmentGateway;
    }

    public void execute(Integer id) {
        appointmentGateway.deleteAppointmentById(id);
    }
}

