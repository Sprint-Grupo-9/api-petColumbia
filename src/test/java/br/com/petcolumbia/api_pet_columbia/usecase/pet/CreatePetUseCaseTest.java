package br.com.petcolumbia.api_pet_columbia.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.CreatePetUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;
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
@DisplayName("CreatePetUseCase - Unit Tests")
class CreatePetUseCaseTest {

    @Mock
    private PetGateway petGateway;

    @Mock
    private OwnerGateway ownerGateway;

    @InjectMocks
    private CreatePetUseCase useCase;

    private PetCreateCommand validCommand;
    private Pet mockPet;
    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        validCommand = new PetCreateCommand(
            "Thor", "cachorro", "Labrador", "g", "curta", 3, "macho"
        );

        mockPet = new Pet(1, null, "Thor", "g", "cachorro", "Labrador", "curta", 3, "macho", null, null, null);

        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
            "$2a$10$encodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should create pet successfully")
    void shouldCreatePetSuccessfully() {
        // Arrange
        Integer ownerId = 1;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(mockOwner);
        when(petGateway.create(any(Pet.class), eq(ownerId))).thenReturn(mockPet);

        // Act
        Pet result = useCase.execute(ownerId, validCommand);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Thor");
        assertThat(result.getSpecies()).isEqualTo("cachorro");
        verify(ownerGateway, times(1)).getOwnerById(ownerId);
        verify(petGateway, times(1)).create(any(Pet.class), eq(ownerId));
    }

    @Test
    @DisplayName("Should validate pet size")
    void shouldValidatePetSize() {
        // Arrange
        Integer ownerId = 1;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(mockOwner);
        when(petGateway.create(any(Pet.class), eq(ownerId))).thenReturn(mockPet);

        // Act
        Pet result = useCase.execute(ownerId, validCommand);

        // Assert
        assertThat(result.getSize()).isIn("pp", "p", "m", "g", "gg");
    }
}

