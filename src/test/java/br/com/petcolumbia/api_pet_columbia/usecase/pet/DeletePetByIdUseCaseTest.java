package br.com.petcolumbia.api_pet_columbia.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.DeletePetByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeletePetByIdUseCase - Unit Tests")
class DeletePetByIdUseCaseTest {

    @Mock
    private PetGateway petGateway;

    @InjectMocks
    private DeletePetByIdUseCase useCase;

    @Test
    @DisplayName("Should delete pet successfully")
    void shouldDeletePetSuccessfully() {
        // Arrange
        Integer petId = 1;
        when(petGateway.existsById(petId)).thenReturn(true);
        doNothing().when(petGateway).deletePetById(petId);

        // Act
        useCase.execute(petId);

        // Assert
        verify(petGateway, times(1)).existsById(petId);
        verify(petGateway, times(1)).deletePetById(petId);
    }
}

