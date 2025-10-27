package br.com.petcolumbia.api_pet_columbia.usecase.pet_offering;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering.ListAllPetOfferingUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;
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
@DisplayName("ListAllPetOfferingUseCase - Unit Tests")
class ListAllPetOfferingUseCaseTest {

    @Mock
    private PetOfferingGateway petOfferingGateway;

    @InjectMocks
    private ListAllPetOfferingUseCase useCase;

    private List<PetOffering> mockPetOfferings;

    @BeforeEach
    void setUp() {
        PetOffering banho = new PetOffering(1, "Banho", "Banho completo", null, null);
        PetOffering tosa = new PetOffering(2, "Tosa higiênica", "Tosa higiênica", null, null);
        PetOffering corte = new PetOffering(9, "Corte de unha", "Corte de unha", null, null);

        mockPetOfferings = Arrays.asList(banho, tosa, corte);
    }

    @Test
    @DisplayName("Should list all pet offerings successfully")
    void shouldListAllPetOfferingsSuccessfully() {
        // Arrange
        when(petOfferingGateway.listAllPetOfferings()).thenReturn(mockPetOfferings);

        // Act
        List<PetOffering> result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Banho");
        verify(petOfferingGateway, times(1)).listAllPetOfferings();
    }

    @Test
    @DisplayName("Should return empty list when no pet offerings exist")
    void shouldReturnEmptyListWhenNoPetOfferings() {
        // Arrange
        when(petOfferingGateway.listAllPetOfferings()).thenReturn(Collections.emptyList());

        // Act
        List<PetOffering> result = useCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should verify all pet offerings have valid ID")
    void shouldVerifyAllPetOfferingsHaveValidId() {
        // Arrange
        when(petOfferingGateway.listAllPetOfferings()).thenReturn(mockPetOfferings);

        // Act
        List<PetOffering> result = useCase.execute();

        // Assert
        assertThat(result).allMatch(po -> po.getId() != null && po.getId() > 0);
    }
}

