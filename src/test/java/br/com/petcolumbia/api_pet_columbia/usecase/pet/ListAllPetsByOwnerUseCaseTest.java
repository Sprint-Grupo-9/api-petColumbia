package br.com.petcolumbia.api_pet_columbia.usecase.pet;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.ListAllPetsByOwnerUseCase;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListAllPetsByOwnerUseCase - Unit Tests")
class ListAllPetsByOwnerUseCaseTest {

    @Mock
    private PetGateway petGateway;

    @Mock
    private OwnerGateway ownerGateway;

    @InjectMocks
    private ListAllPetsByOwnerUseCase useCase;

    private List<Pet> mockPets;
    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        Pet thor = new Pet(1, null, "Thor", "g", "cachorro", "Labrador", "curta", 3, "macho", null, null, null);
        Pet mel = new Pet(2, null, "Mel", "p", "cachorro", "Poodle", "longa", 2, "fêmea", null, null, null);

        mockPets = Arrays.asList(thor, mel);

        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
                "$2a$10$encodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should list all pets by owner successfully")
    void shouldListAllPetsByOwnerSuccessfully() {
        // Arrange
        Integer ownerId = 1;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(mockOwner);
        when(petGateway.listAllPetsByOwner(ownerId)).thenReturn(mockPets);

        // Act
        List<Pet> result = useCase.execute(ownerId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Thor");
        assertThat(result.get(1).getName()).isEqualTo("Mel");
        verify(ownerGateway, times(1)).getOwnerById(ownerId);
        verify(petGateway, times(1)).listAllPetsByOwner(ownerId);
    }

    @Test
    @DisplayName("Should return empty list when owner has no pets")
    void shouldReturnEmptyListWhenOwnerHasNoPets() {
        // Arrange
        Integer ownerId = 999;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(mockOwner);
        when(petGateway.listAllPetsByOwner(ownerId)).thenReturn(Collections.emptyList());

        // Act
        List<Pet> result = useCase.execute(ownerId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

