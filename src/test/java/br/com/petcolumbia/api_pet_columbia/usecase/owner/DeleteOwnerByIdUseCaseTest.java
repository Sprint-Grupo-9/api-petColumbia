package br.com.petcolumbia.api_pet_columbia.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.DeleteOwnerByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeleteOwnerByIdUseCase - Unit Tests")
class DeleteOwnerByIdUseCaseTest {

    @Mock
    private OwnerGateway ownerGateway;

    @InjectMocks
    private DeleteOwnerByIdUseCase useCase;

    @Test
    @DisplayName("Should delete owner successfully")
    void shouldDeleteOwnerSuccessfully() {
        // Arrange
        Integer ownerId = 1;
        doNothing().when(ownerGateway).deleteOwnerById(ownerId);

        // Act
        useCase.execute(ownerId);

        // Assert
        verify(ownerGateway, times(1)).deleteOwnerById(ownerId);
    }
}

