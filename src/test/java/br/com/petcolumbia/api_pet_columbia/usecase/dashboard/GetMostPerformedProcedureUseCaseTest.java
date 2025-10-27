package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetMostPerformedProcedureUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetMostPerformedProcedureUseCase - Unit Tests")
class GetMostPerformedProcedureUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetMostPerformedProcedureUseCase useCase;

    private TopServiceResponseDto mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new TopServiceResponseDto("Banho", 120L, LocalDate.now().minusDays(30), LocalDate.now());
    }

    @Test
    @DisplayName("Should get most performed procedure successfully")
    void shouldGetMostPerformedProcedureSuccessfully() {
        // Arrange
        when(dashboardGateway.getMostPerformedProcedureLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopServiceResponseDto result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getProcedureName()).isEqualTo("Banho");
        assertThat(result.getCount()).isEqualTo(120L);
        verify(dashboardGateway, times(1)).getMostPerformedProcedureLastThirtyDays();
    }

    @Test
    @DisplayName("Should validate count is positive")
    void shouldValidateCountIsPositive() {
        // Arrange
        when(dashboardGateway.getMostPerformedProcedureLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopServiceResponseDto result = useCase.execute();

        // Assert
        assertThat(result.getCount()).isPositive();
    }
}
