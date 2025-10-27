package br.com.petcolumbia.api_pet_columbia.usecase.employee;

import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee.FindEmployeeByIdUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FindEmployeeByIdUseCase - Unit Tests")
class FindEmployeeByIdUseCaseTest {

    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private FindEmployeeByIdUseCase useCase;

    private Employee mockEmployee;

    @BeforeEach
    void setUp() {
        mockEmployee = new Employee(1, "Priscila", null, null);
    }

    @Test
    @DisplayName("Should find employee by ID successfully")
    void shouldFindEmployeeByIdSuccessfully() {
        // Arrange
        when(employeeGateway.findEmployeeById(1)).thenReturn(mockEmployee);

        // Act
        Employee result = useCase.execute(1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Priscila");
        verify(employeeGateway, times(1)).findEmployeeById(1);
    }
}

