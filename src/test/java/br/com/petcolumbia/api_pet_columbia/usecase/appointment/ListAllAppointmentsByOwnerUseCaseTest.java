package br.com.petcolumbia.api_pet_columbia.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.ListAllAppointmentsByOwnerUseCase;
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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListAllAppointmentsByOwnerUseCase - Unit Tests")
class ListAllAppointmentsByOwnerUseCaseTest {

    @Mock
    private AppointmentGateway appointmentGateway;

    @InjectMocks
    private ListAllAppointmentsByOwnerUseCase useCase;

    private List<Appointment> mockAppointments;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();

        Appointment app1 = new Appointment(
            1, null, null, "Obs 1", false, "Banho", 100.0,
            now, now.plusHours(1), false, now, now
        );
        Appointment app2 = new Appointment(
            2, null, null, "Obs 2", false, "Tosa", 80.0,
            now.plusDays(1), now.plusDays(1).plusHours(1), false, now, now
        );

        mockAppointments = Arrays.asList(app1, app2);
    }

    @Test
    @DisplayName("Should list all appointments by owner successfully")
    void shouldListAllAppointmentsByOwnerSuccessfully() {
        // Arrange
        Integer ownerId = 1;
        when(appointmentGateway.findAllAppointmentsByOwnerId(ownerId)).thenReturn(mockAppointments);

        // Act
        List<Appointment> result = useCase.execute(ownerId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(appointmentGateway, times(1)).findAllAppointmentsByOwnerId(ownerId);
    }

    @Test
    @DisplayName("Should return empty list when owner has no appointments")
    void shouldReturnEmptyListWhenOwnerHasNoAppointments() {
        // Arrange
        Integer ownerId = 999;
        when(appointmentGateway.findAllAppointmentsByOwnerId(ownerId)).thenReturn(Collections.emptyList());

        // Act
        List<Appointment> result = useCase.execute(ownerId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

