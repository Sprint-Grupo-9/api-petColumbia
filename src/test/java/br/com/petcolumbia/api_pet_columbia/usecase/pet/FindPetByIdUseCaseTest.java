package br.com.petcolumbia.api_pet_columbia.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.FindPetByIdUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FindPetByIdUseCase - Unit Tests")
class FindPetByIdUseCaseTest {

    @Mock
    private PetGateway petGateway;

    @InjectMocks
    private FindPetByIdUseCase useCase;

    private Pet mockPet;

    @BeforeEach
    void setUp() {
        mockPet = new Pet(1, null, "Thor", "g", "cachorro", "Labrador", "curta", 3, "macho", null, null, null);
    }

    @Test
    @DisplayName("Should find pet by ID successfully")
    void shouldFindPetByIdSuccessfully() {
        // Arrange
        Integer petId = 1;
        Integer ownerId = 1;
        when(petGateway.findPetById(petId, ownerId)).thenReturn(mockPet);

        // Act
        Pet result = useCase.execute(petId, ownerId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Thor");
        verify(petGateway, times(1)).findPetById(petId, ownerId);
    }

    @Test
    @DisplayName("Should throw exception when pet not found")
    void shouldThrowExceptionWhenPetNotFound() {
        // Arrange
        Integer petId = 999;
        Integer ownerId = 1;
        when(petGateway.findPetById(petId, ownerId))
            .thenThrow(new EntityNotFoundException("Pet não encontrado"));

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(petId, ownerId))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessageContaining("não encontrado");
    }
}

