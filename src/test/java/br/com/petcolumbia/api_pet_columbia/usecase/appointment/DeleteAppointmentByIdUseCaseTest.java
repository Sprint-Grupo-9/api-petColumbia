package br.com.petcolumbia.api_pet_columbia.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.DeleteAppointmentByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeleteAppointmentByIdUseCase - Unit Tests")
class DeleteAppointmentByIdUseCaseTest {

    @Mock
    private AppointmentGateway appointmentGateway;

    @InjectMocks
    private DeleteAppointmentByIdUseCase useCase;

    @Test
    @DisplayName("Should delete appointment successfully")
    void shouldDeleteAppointmentSuccessfully() {
        // Arrange
        Integer appointmentId = 1;
        doNothing().when(appointmentGateway).deleteAppointmentById(appointmentId);

        // Act
        useCase.execute(appointmentId);

        // Assert
        verify(appointmentGateway, times(1)).deleteAppointmentById(appointmentId);
    }
}

