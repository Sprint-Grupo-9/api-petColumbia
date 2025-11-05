package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopPetOfferingsTimingResponse;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetMostPetOfferingsTimingUseCase;
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
@DisplayName("GetMostPetOfferingsTimingUseCase - Unit Tests")
class GetMostPetOfferingsTimingUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetMostPetOfferingsTimingUseCase useCase;

    private TopPetOfferingsTimingResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new TopPetOfferingsTimingResponse(
            LocalDate.now().minusDays(30),
            LocalDate.now(),
            LocalTime.of(9, 0),
            35L
        );
    }

    @Test
    @DisplayName("Should get most pet offerings timing successfully")
    void shouldGetMostPetOfferingsTimingSuccessfully() {
        // Arrange
        when(dashboardGateway.getMostPetOfferingsTimingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopPetOfferingsTimingResponse result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getHour()).isEqualTo(LocalTime.of(9, 0));
        assertThat(result.getCount()).isEqualTo(35L);
        verify(dashboardGateway, times(1)).getMostPetOfferingsTimingLastThirtyDays();
    }

    @Test
    @DisplayName("Should validate count is positive")
    void shouldValidateCountIsPositive() {
        // Arrange
        when(dashboardGateway.getMostPetOfferingsTimingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopPetOfferingsTimingResponse result = useCase.execute();

        // Assert
        assertThat(result.getCount()).isPositive();
    }
}

