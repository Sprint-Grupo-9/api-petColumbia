package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.AppointmentsDashboardInfosResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastAppointmentsDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastAppointmentsListDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LastPetAndOwnerAppointmentsResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetAppointmentsByDateUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetAppointmentsByDateUseCase - Unit Tests")
class GetAppointmentsByDateUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetAppointmentsByDateUseCase useCase;

    private List<Appointment> mockAppointments;
    private LocalDate testDate;
    private Pet mockPet;
    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        testDate = LocalDate.of(2025, 1, 26);
        LocalDateTime dateTime = testDate.atTime(10, 0);

        // Criar Owner completo
        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
            "$2a$10$encodedPassword", address, false, null);

        // Criar Pet completo com Owner
        mockPet = new Pet(1, mockOwner, "Thor", "g", "cachorro", "Labrador", "curta", 3, "macho", null, null, null);

        // Criar Appointment com Pet completo
        Appointment app1 = new Appointment(
            1, mockPet, null, "Obs 1", false, "Banho", 100.0,
            dateTime, dateTime.plusHours(1), false, dateTime, dateTime
        );

        mockAppointments = Collections.singletonList(app1);
    }

    @Test
    @DisplayName("Should get appointments by date successfully")
    void shouldGetAppointmentsByDateSuccessfully() {
        // Arrange
        // Criar LastAppointmentsDto para pet e owner
        LastAppointmentsDto petLastAppointment = new LastAppointmentsDto(
            1, // petId
            100, // appointmentId anterior
            testDate.minusDays(5), // data do último agendamento
            testDate.minusDays(5).atTime(10, 0).toLocalTime(), // start
            testDate.minusDays(5).atTime(11, 0).toLocalTime(), // end
            "Banho", // proceduresNames
            80.0 // price
        );

        LastAppointmentsDto ownerLastAppointment = new LastAppointmentsDto(
            1, // ownerId
            101, // appointmentId anterior
            testDate.minusDays(3), // data do último agendamento
            testDate.minusDays(3).atTime(14, 0).toLocalTime(), // start
            testDate.minusDays(3).atTime(15, 0).toLocalTime(), // end
            "Tosa", // proceduresNames
            60.0 // price
        );

        // Criar listas com 1 elemento (mesmo tamanho da lista de appointments)
        LastAppointmentsListDto lastPetDto = new LastAppointmentsListDto(
            Collections.singletonList(petLastAppointment)
        );
        LastAppointmentsListDto lastOwnerDto = new LastAppointmentsListDto(
            Collections.singletonList(ownerLastAppointment)
        );

        LastPetAndOwnerAppointmentsResponseDto lastAppointments = new LastPetAndOwnerAppointmentsResponseDto(
            Collections.singletonList(lastPetDto),
            Collections.singletonList(lastOwnerDto)
        );

        when(dashboardGateway.findAppointmentsByDate(testDate)).thenReturn(mockAppointments);
        when(dashboardGateway.getLastAppointmentsOfPetAndOwner(mockAppointments)).thenReturn(lastAppointments);

        // Act
        AppointmentsDashboardInfosResponseDto result = useCase.execute(testDate);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCardResponses()).isNotNull();
        assertThat(result.getInfoResponses()).isNotNull();
        verify(dashboardGateway, times(1)).findAppointmentsByDate(testDate);
        verify(dashboardGateway, times(1)).getLastAppointmentsOfPetAndOwner(mockAppointments);
    }

    @Test
    @DisplayName("Should return null when no appointments")
    void shouldReturnNullWhenNoAppointments() {
        // Arrange
        when(dashboardGateway.findAppointmentsByDate(testDate)).thenReturn(Collections.emptyList());

        // Act
        AppointmentsDashboardInfosResponseDto result = useCase.execute(testDate);

        // Assert
        assertThat(result).isNull();
        verify(dashboardGateway, times(1)).findAppointmentsByDate(testDate);
        verify(dashboardGateway, never()).getLastAppointmentsOfPetAndOwner(any());
    }
}

