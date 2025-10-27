package br.com.petcolumbia.api_pet_columbia.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.UpdatePetByIdUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UpdatePetByIdUseCase - Unit Tests")
class UpdatePetByIdUseCaseTest {

    @Mock
    private PetGateway petGateway;

    @InjectMocks
    private UpdatePetByIdUseCase useCase;

    private PetUpdateCommand updateCommand;
    private Pet mockPet;

    @BeforeEach
    void setUp() {
        updateCommand = new PetUpdateCommand(
            "Thor Updated", "cachorro", "Labrador", "gg", "curta", 4, "macho"
        );

        mockPet = new Pet(1, null, "Thor Updated", "gg", "cachorro", "Labrador", "curta", 4, "macho", null, null, null);
    }

    @Test
    @DisplayName("Should update pet successfully")
    void shouldUpdatePetSuccessfully() {
        // Arrange
        Integer petId = 1;
        when(petGateway.updatePetById(eq(petId), any(PetUpdateCommand.class))).thenReturn(mockPet);

        // Act
        Pet result = useCase.execute(petId, updateCommand);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Thor Updated");
        assertThat(result.getAge()).isEqualTo(4);
        assertThat(result.getSize()).isEqualTo("gg");
        verify(petGateway, times(1)).updatePetById(eq(petId), any(PetUpdateCommand.class));
    }
}

