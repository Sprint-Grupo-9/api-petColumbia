package br.com.petcolumbia.api_pet_columbia.usecase.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering.GetPetOfferingsPricesByPetUseCase;
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
@DisplayName("GetPetOfferingsPricesByPetUseCase - Unit Tests")
class GetPetOfferingsPricesByPetUseCaseTest {

    @Mock
    private PetOfferingPriceAndDurationGateway priceAndDurationGateway;

    @InjectMocks
    private GetPetOfferingsPricesByPetUseCase useCase;

    private List<Integer> petOfferingIds;
    private List<PetOfferingWithPriceResponseDto> mockPrices;

    @BeforeEach
    void setUp() {
        petOfferingIds = Arrays.asList(1, 2, 9);

        mockPrices = Arrays.asList(
            new PetOfferingWithPriceResponseDto(1, "Banho", 100.0),
            new PetOfferingWithPriceResponseDto(2, "Tosa higiênica", 20.0),
            new PetOfferingWithPriceResponseDto(9, "Corte de unha", 20.0)
        );
    }

    @Test
    @DisplayName("Should get prices by pet and pet offerings successfully")
    void shouldGetPricesByPetAndPetOfferingsSuccessfully() {
        // Arrange
        Integer petId = 1;
        when(priceAndDurationGateway.getPetOfferingsPricesByPetIdAndPetOfferingIds(petId, petOfferingIds))
            .thenReturn(mockPrices);

        // Act
        List<PetOfferingWithPriceResponseDto> result = useCase.execute(petId, petOfferingIds);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getProcedureName()).isEqualTo("Banho");
        verify(priceAndDurationGateway, times(1))
            .getPetOfferingsPricesByPetIdAndPetOfferingIds(petId, petOfferingIds);
    }

    @Test
    @DisplayName("Should calculate total price correctly")
    void shouldCalculateTotalPriceCorrectly() {
        // Arrange
        Integer petId = 1;
        when(priceAndDurationGateway.getPetOfferingsPricesByPetIdAndPetOfferingIds(petId, petOfferingIds))
            .thenReturn(mockPrices);

        // Act
        List<PetOfferingWithPriceResponseDto> result = useCase.execute(petId, petOfferingIds);
        Double totalPrice = result.stream().mapToDouble(PetOfferingWithPriceResponseDto::getPrice).sum();

        // Assert
        assertThat(totalPrice).isEqualTo(140.0);
    }

    @Test
    @DisplayName("Should return empty list when no pet offerings")
    void shouldReturnEmptyListWhenNoPetOfferings() {
        // Arrange
        Integer petId = 1;
        when(priceAndDurationGateway.getPetOfferingsPricesByPetIdAndPetOfferingIds(petId, Collections.emptyList()))
            .thenReturn(Collections.emptyList());

        // Act
        List<PetOfferingWithPriceResponseDto> result = useCase.execute(petId, Collections.emptyList());

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

