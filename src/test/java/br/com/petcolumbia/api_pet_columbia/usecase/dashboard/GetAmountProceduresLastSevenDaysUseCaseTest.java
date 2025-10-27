package br.com.petcolumbia.api_pet_columbia.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.GetAmountProceduresLastSevenDaysUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetAmountProceduresLastSevenDaysUseCase - Unit Tests")
class GetAmountProceduresLastSevenDaysUseCaseTest {

    @Mock
    private DashboardGateway dashboardGateway;

    @InjectMocks
    private GetAmountProceduresLastSevenDaysUseCase useCase;

    @Test
    @DisplayName("Should get amount of procedures last seven days successfully")
    void shouldGetAmountOfProceduresLastSevenDaysSuccessfully() {
        // Arrange
        Map<LocalDate, Long> mockData = new HashMap<>();
        mockData.put(LocalDate.now().minusDays(6), 10L);
        mockData.put(LocalDate.now().minusDays(5), 15L);
        mockData.put(LocalDate.now().minusDays(4), 8L);
        mockData.put(LocalDate.now(), 20L);

        when(dashboardGateway.getAmountProceduresCountPerDay()).thenReturn(mockData);

        // Act
        Map<LocalDate, Long> result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        verify(dashboardGateway, times(1)).getAmountProceduresCountPerDay();
    }

    @Test
    @DisplayName("Should return empty map when no procedures")
    void shouldReturnEmptyMapWhenNoProcedures() {
        // Arrange
        when(dashboardGateway.getAmountProceduresCountPerDay()).thenReturn(new HashMap<>());

        // Act
        Map<LocalDate, Long> result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

