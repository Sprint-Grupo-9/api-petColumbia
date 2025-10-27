package br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;

import java.util.List;

public class ListAllAppointmentsByOwnerUseCase {

    private final AppointmentGateway appointmentGateway;

    public ListAllAppointmentsByOwnerUseCase(AppointmentGateway appointmentGateway) {
        this.appointmentGateway = appointmentGateway;
    }

    public List<Appointment> execute(Integer ownerId) {
        return appointmentGateway.findAllAppointmentsByOwnerId(ownerId);
    }
}

