package br.com.petcolumbia.api_pet_columbia.core.application.command.appointment;

import java.time.LocalDateTime;

public record AppointmentCreateCommand(
        Integer petId,
        Integer employeeId,
        String servicesNames,
        Double totalPrice,
        String observations,
        Boolean taxiService,
        LocalDateTime startDateTime,
        Integer durationMinutes
) {
}

