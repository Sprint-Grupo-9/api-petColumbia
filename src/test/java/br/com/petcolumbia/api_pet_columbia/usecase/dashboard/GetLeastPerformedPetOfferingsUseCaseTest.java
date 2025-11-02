package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LeastPetOfferingResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetLeastPerformedPetOfferingsUseCase;
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
@DisplayName("GetLeastPerformedPetOfferingsUseCase - Unit Tests")
class GetLeastPerformedPetOfferingsUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetLeastPerformedPetOfferingsUseCase useCase;

    private LeastPetOfferingResponseDto mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new LeastPetOfferingResponseDto("Hidratação", 5L, LocalDate.now().minusDays(30), LocalDate.now());
    }

    @Test
    @DisplayName("Should get least performed pet offering successfully")
    void shouldGetLeastPerformedPetOfferingSuccessfully() {
        // Arrange
        when(dashboardGateway.getLeastPerformedPetOfferingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        LeastPetOfferingResponseDto result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getPetOfferingName()).isEqualTo("Hidratação");
        assertThat(result.getCount()).isEqualTo(5L);
        verify(dashboardGateway, times(1)).getLeastPerformedPetOfferingLastThirtyDays();
    }

    @Test
    @DisplayName("Should validate count is not negative")
    void shouldValidateCountIsNotNegative() {
        // Arrange
        when(dashboardGateway.getLeastPerformedPetOfferingLastThirtyDays()).thenReturn(mockResponse);

        // Act
        LeastPetOfferingResponseDto result = useCase.execute();

        // Assert
        assertThat(result.getCount()).isNotNegative();
    }
}

