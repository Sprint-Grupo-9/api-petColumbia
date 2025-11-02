package br.com.petcolumbia.api_pet_columbia.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AvailableTimesResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.BusyTimeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.GetAvailableTimesUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetAvailableTimesUseCase - Unit Tests")
class GetAvailableTimesUseCaseTest {

    @Mock
    private AppointmentGateway appointmentGateway;

    @Mock
    private PetOfferingGateway petOfferingGateway;

    @Mock
    private PetOfferingPriceAndDurationGateway priceAndDurationGateway;

    @Mock
    private EmployeeGateway employeeGateway;

    @Mock
    private PetGateway petGateway;

    @InjectMocks
    private GetAvailableTimesUseCase useCase;

    private List<BusyTimeResponseDto> mockBusyTimes;
    private Pet mockPet;
    private List<Employee> mockEmployees;

    @BeforeEach
    void setUp() {
        mockBusyTimes = Arrays.asList(
            new BusyTimeResponseDto(LocalTime.of(9, 0), LocalTime.of(10, 0)),
            new BusyTimeResponseDto(LocalTime.of(14, 0), LocalTime.of(15, 0))
        );

        mockPet = new Pet(1, null, "Thor", "g", "cachorro", "Labrador", "curta", 3, "macho", null, null, null);

        Employee employee1 = new Employee(1, "Priscila", null, null);
        mockEmployees = Collections.singletonList(employee1);
    }

    @Test
    @DisplayName("Should get available times successfully")
    void shouldGetAvailableTimesSuccessfully() {
        // Arrange
        LocalDate date = LocalDate.of(2025, 1, 26);
        Integer petId = 1;
        List<Integer> petOfferingIds = Arrays.asList(1, 2);

        when(petGateway.findPetById(petId, null)).thenReturn(mockPet);
        when(petOfferingGateway.getPetOfferingsNamesByIds(petOfferingIds)).thenReturn("Banho, Tosa");
        when(employeeGateway.listEmployeesByPetOfferings(petOfferingIds)).thenReturn(mockEmployees);
        when(priceAndDurationGateway.calculateTotalPrice(petOfferingIds, "g", "curta")).thenReturn(140.0);
        when(priceAndDurationGateway.calculateTotalDuration(petOfferingIds, "g", "curta")).thenReturn(120);
        when(appointmentGateway.findBusyTimesByEmployeeAndDate(anyInt(), eq(date))).thenReturn(mockBusyTimes);

        // Act
        List<AvailableTimesResponseDto> result = useCase.execute(date, petId, petOfferingIds);

        // Assert
        assertThat(result).isNotNull();
        verify(petGateway, times(1)).findPetById(petId, null);
        verify(employeeGateway, times(1)).listEmployeesByPetOfferings(petOfferingIds);
    }

    @Test
    @DisplayName("Should return available times when no busy times")
    void shouldReturnAvailableTimesWhenNoBusyTimes() {
        // Arrange
        LocalDate date = LocalDate.of(2025, 1, 26);
        Integer petId = 1;
        List<Integer> petOfferingIds = Arrays.asList(1, 2);

        when(petGateway.findPetById(petId, null)).thenReturn(mockPet);
        when(petOfferingGateway.getPetOfferingsNamesByIds(petOfferingIds)).thenReturn("Banho, Tosa");
        when(employeeGateway.listEmployeesByPetOfferings(petOfferingIds)).thenReturn(mockEmployees);
        when(priceAndDurationGateway.calculateTotalPrice(petOfferingIds, "g", "curta")).thenReturn(140.0);
        when(priceAndDurationGateway.calculateTotalDuration(petOfferingIds, "g", "curta")).thenReturn(120);
        when(appointmentGateway.findBusyTimesByEmployeeAndDate(anyInt(), eq(date))).thenReturn(Collections.emptyList());

        // Act
        List<AvailableTimesResponseDto> result = useCase.execute(date, petId, petOfferingIds);

        // Assert
        assertThat(result).isNotNull();
    }
}

