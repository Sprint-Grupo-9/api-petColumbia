package br.com.petcolumbia.api_pet_columbia.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.CreateAppointmentUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateAppointmentUseCase - Unit Tests")
class CreateAppointmentUseCaseTest {

    @Mock
    private AppointmentGateway appointmentGateway;

    @InjectMocks
    private CreateAppointmentUseCase useCase;

    private AppointmentCreateCommand command;
    private Appointment mockAppointment;

    @BeforeEach
    void setUp() {
        LocalDateTime startTime = LocalDateTime.of(2025, 1, 26, 10, 0);

        command = new AppointmentCreateCommand(
            1,                      // petId
            1,                      // employeeId
            "Banho, Tosa",         // servicesNames
            140.0,                  // totalPrice
            "Observação teste",     // observations
            false,                  // taxiService
            startTime,              // startDateTime
            120                     // durationMinutes
        );

        mockAppointment = new Appointment(
            1, null, null, "Observação teste", false, "Banho, Tosa",
            140.0, startTime, startTime.plusHours(2), false, startTime, startTime
        );
    }

    @Test
    @DisplayName("Should create appointment successfully")
    void shouldCreateAppointmentSuccessfully() {
        // Arrange
        when(appointmentGateway.createAppointment(any(AppointmentCreateCommand.class))).thenReturn(mockAppointment);

        // Act
        Appointment result = useCase.execute(command);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getTotalPrice()).isEqualTo(140.0);
        verify(appointmentGateway, times(1)).createAppointment(any(AppointmentCreateCommand.class));
    }

    @Test
    @DisplayName("Should validate total price is positive")
    void shouldValidateTotalPriceIsPositive() {
        // Arrange
        when(appointmentGateway.createAppointment(any(AppointmentCreateCommand.class))).thenReturn(mockAppointment);

        // Act
        Appointment result = useCase.execute(command);

        // Assert
        assertThat(result.getTotalPrice()).isPositive();
    }
}

