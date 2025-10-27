package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopProceduresTimingResponse;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetMostProceduresTimingUseCase;
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
@DisplayName("GetMostProceduresTimingUseCase - Unit Tests")
class GetMostProceduresTimingUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetMostProceduresTimingUseCase useCase;

    private TopProceduresTimingResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new TopProceduresTimingResponse(
            LocalDate.now().minusDays(30),
            LocalDate.now(),
            LocalTime.of(9, 0),
            35L
        );
    }

    @Test
    @DisplayName("Should get most procedures timing successfully")
    void shouldGetMostProceduresTimingSuccessfully() {
        // Arrange
        when(dashboardGateway.getMostProceduresTimingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopProceduresTimingResponse result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getHour()).isEqualTo(LocalTime.of(9, 0));
        assertThat(result.getCount()).isEqualTo(35L);
        verify(dashboardGateway, times(1)).getMostProceduresTimingLastThirtyDays();
    }

    @Test
    @DisplayName("Should validate count is positive")
    void shouldValidateCountIsPositive() {
        // Arrange
        when(dashboardGateway.getMostProceduresTimingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopProceduresTimingResponse result = useCase.execute();

        // Assert
        assertThat(result.getCount()).isPositive();
    }
}

