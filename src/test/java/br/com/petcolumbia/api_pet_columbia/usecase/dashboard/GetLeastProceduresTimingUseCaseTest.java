package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LeastProceduresTimingResponse;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetLeastProceduresTimingUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetLeastProceduresTimingUseCase - Unit Tests")
class GetLeastProceduresTimingUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetLeastProceduresTimingUseCase useCase;

    private LeastProceduresTimingResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new LeastProceduresTimingResponse(
            LocalDate.now().minusDays(30),
            LocalDate.now(),
            LocalTime.of(16, 0),
            3L
        );
    }

    @Test
    @DisplayName("Should get least procedures timing successfully")
    void shouldGetLeastProceduresTimingSuccessfully() {
        // Arrange
        when(dashboardGateway.getLeastProceduresTimingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        LeastProceduresTimingResponse result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getHour()).isEqualTo(LocalTime.of(16, 0));
        assertThat(result.getCount()).isEqualTo(3L);
        verify(dashboardGateway, times(1)).getLeastProceduresTimingLastThirtyDays();
    }

    @Test
    @DisplayName("Should validate count is not negative")
    void shouldValidateCountIsNotNegative() {
        // Arrange
        when(dashboardGateway.getLeastProceduresTimingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        LeastProceduresTimingResponse result = useCase.execute();

        // Assert
        assertThat(result.getCount()).isNotNegative();
    }
}

