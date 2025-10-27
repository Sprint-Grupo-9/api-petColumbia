package br.com.petcolumbia.api_pet_columbia.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.ListAppointmentsByDateUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListAppointmentsByDateUseCase - Unit Tests")
class ListAppointmentsByDateUseCaseTest {

    @Mock
    private AppointmentGateway appointmentGateway;

    @InjectMocks
    private ListAppointmentsByDateUseCase useCase;

    private List<Appointment> mockAppointments;
    private LocalDate testDate;

    @BeforeEach
    void setUp() {
        testDate = LocalDate.of(2025, 1, 26);
        LocalDateTime dateTime = testDate.atTime(10, 0);

        Appointment app1 = new Appointment(
            1, null, null, "Obs 1", false, "Banho", 100.0,
            dateTime, dateTime.plusHours(1), false, dateTime, dateTime
        );
        Appointment app2 = new Appointment(
            2, null, null, "Obs 2", false, "Tosa", 80.0,
            dateTime.plusHours(2), dateTime.plusHours(3), false, dateTime, dateTime
        );

        mockAppointments = Arrays.asList(app1, app2);
    }

    @Test
    @DisplayName("Should list appointments by date successfully")
    void shouldListAppointmentsByDateSuccessfully() {
        // Arrange
        when(appointmentGateway.findAppointmentsByDate(testDate)).thenReturn(mockAppointments);

        // Act
        List<Appointment> result = useCase.execute(testDate);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(appointmentGateway, times(1)).findAppointmentsByDate(testDate);
    }

    @Test
    @DisplayName("Should return empty list when no appointments on date")
    void shouldReturnEmptyListWhenNoAppointmentsOnDate() {
        // Arrange
        LocalDate emptyDate = LocalDate.of(2025, 12, 31);
        when(appointmentGateway.findAppointmentsByDate(emptyDate)).thenReturn(Collections.emptyList());

        // Act
        List<Appointment> result = useCase.execute(emptyDate);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

