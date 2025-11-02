package br.com.petcolumbia.api_pet_columbia.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.UpdateAppointmentByIdUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UpdateAppointmentByIdUseCase - Unit Tests")
class UpdateAppointmentByIdUseCaseTest {

    @Mock
    private AppointmentGateway appointmentGateway;

    @InjectMocks
    private UpdateAppointmentByIdUseCase useCase;

    private AppointmentUpdateCommand updateCommand;
    private Appointment mockAppointment;

    @BeforeEach
    void setUp() {
        LocalDateTime startTime = LocalDateTime.of(2025, 1, 26, 14, 0);

        updateCommand = new AppointmentUpdateCommand(
            1, 1, "Banho, Tosa", 140.0, "Observação atualizada",
            true, startTime, 120
        );

        mockAppointment = new Appointment(
            1, null, null, "Observação atualizada", true, "Banho, Tosa",
            140.0, startTime, startTime.plusHours(2), false,
            LocalDateTime.now(), LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Should update appointment successfully")
    void shouldUpdateAppointmentSuccessfully() {
        // Arrange
        Integer appointmentId = 1;
        when(appointmentGateway.updateAppointmentById(eq(appointmentId), any(AppointmentUpdateCommand.class)))
            .thenReturn(mockAppointment);

        // Act
        Appointment result = useCase.execute(appointmentId, updateCommand);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getObservations()).isEqualTo("Observação atualizada");
        assertThat(result.getTaxiService()).isTrue();
        verify(appointmentGateway, times(1)).updateAppointmentById(eq(appointmentId), any(AppointmentUpdateCommand.class));
    }
}

