package br.com.petcolumbia.api_pet_columbia.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.UpdateOwnerUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;
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
@DisplayName("UpdateOwnerUseCase - Unit Tests")
class UpdateOwnerUseCaseTest {

    @Mock
    private OwnerGateway ownerGateway;

    @InjectMocks
    private UpdateOwnerUseCase useCase;

    private OwnerUpdateCommand updateCommand;
    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        updateCommand = new OwnerUpdateCommand(
            "João Silva Updated", "11987654321", "01234567",
            "Rua Nova", "456", "Casa", "Bairro Novo"
        );

        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Bairro Novo", "Rua Nova", "456", "Casa");
        mockOwner = new Owner(1, "João Silva Updated", personalInfo, "joao@email.com",
            "$2a$10$encodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should update owner successfully")
    void shouldUpdateOwnerSuccessfully() {
        // Arrange
        Integer ownerId = 1;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(mockOwner);
        when(ownerGateway.updateOwnerById(eq(ownerId), any(OwnerUpdateCommand.class))).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(ownerId, updateCommand);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("João Silva Updated");
        verify(ownerGateway, times(1)).getOwnerById(ownerId);
        verify(ownerGateway, times(1)).updateOwnerById(eq(ownerId), any(OwnerUpdateCommand.class));
    }
}

