package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopPetOfferingResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetMostPerformedPetOfferingsUseCase;
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
@DisplayName("GetMostPerformedPetOfferingsUseCase - Unit Tests")
class GetMostPerformedPetOfferingsUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetMostPerformedPetOfferingsUseCase useCase;

    private TopPetOfferingResponseDto mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new TopPetOfferingResponseDto("Banho", 120L, LocalDate.now().minusDays(30), LocalDate.now());
    }

    @Test
    @DisplayName("Should get most performed pet offering successfully")
    void shouldGetMostPerformedPetOfferingSuccessfully() {
        // Arrange
        when(dashboardGateway.getMostPerformedPetOfferingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopPetOfferingResponseDto result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getPetOfferingName()).isEqualTo("Banho");
        assertThat(result.getCount()).isEqualTo(120L);
        verify(dashboardGateway, times(1)).getMostPerformedPetOfferingLastThirtyDays();
    }

    @Test
    @DisplayName("Should validate count is positive")
    void shouldValidateCountIsPositive() {
        // Arrange
        when(dashboardGateway.getMostPerformedPetOfferingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        TopPetOfferingResponseDto result = useCase.execute();

        // Assert
        assertThat(result.getCount()).isPositive();
    }
}
