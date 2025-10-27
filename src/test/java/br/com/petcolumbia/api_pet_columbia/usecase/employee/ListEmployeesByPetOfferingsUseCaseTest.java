package br.com.petcolumbia.api_pet_columbia.usecase.employee;

import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee.ListEmployeesByPetOfferingsUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListEmployeesByPetOfferingsUseCase - Unit Tests")
class ListEmployeesByPetOfferingsUseCaseTest {

    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private ListEmployeesByPetOfferingsUseCase useCase;

    private List<Employee> mockEmployees;

    @BeforeEach
    void setUp() {
        Employee priscila = new Employee(1, "Priscila", null, null);
        Employee rute = new Employee(2, "Rute", null, null);

        mockEmployees = Arrays.asList(priscila, rute);
    }

    @Test
    @DisplayName("Should list employees by pet offerings successfully")
    void shouldListEmployeesByPetOfferingsSuccessfully() {
        // Arrange
        List<Integer> petOfferingIds = Arrays.asList(1, 2, 3);
        when(employeeGateway.listEmployeesByPetOfferings(petOfferingIds)).thenReturn(mockEmployees);

        // Act
        List<Employee> result = useCase.execute(petOfferingIds);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Priscila");
        verify(employeeGateway, times(1)).listEmployeesByPetOfferings(petOfferingIds);
    }

    @Test
    @DisplayName("Should return empty list when no employees found")
    void shouldReturnEmptyListWhenNoEmployeesFound() {
        // Arrange
        List<Integer> petOfferingIds = Arrays.asList(999);
        when(employeeGateway.listEmployeesByPetOfferings(petOfferingIds)).thenReturn(Collections.emptyList());

        // Act
        List<Employee> result = useCase.execute(petOfferingIds);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

