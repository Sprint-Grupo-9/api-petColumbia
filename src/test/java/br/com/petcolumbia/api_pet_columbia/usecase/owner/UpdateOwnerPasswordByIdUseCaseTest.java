package br.com.petcolumbia.api_pet_columbia.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerCredentialsGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.UpdateOwnerPasswordByIdUseCase;
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
@DisplayName("UpdateOwnerPasswordByIdUseCase - Unit Tests")
class UpdateOwnerPasswordByIdUseCaseTest {

    @Mock
    private OwnerGateway ownerGateway;

    @Mock
    private OwnerCredentialsGateway ownerCredentialsGateway;

    @InjectMocks
    private UpdateOwnerPasswordByIdUseCase useCase;

    private OwnerUpdatePasswordCommand command;
    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        command = new OwnerUpdatePasswordCommand("OldPassword123", "NewPassword123");

        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
            "$2a$10$newEncodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should update password successfully")
    void shouldUpdatePasswordSuccessfully() {
        // Arrange
        Integer ownerId = 1;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(mockOwner);
        when(ownerCredentialsGateway.updatePasswordById(eq(ownerId), any(OwnerUpdatePasswordCommand.class)))
            .thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(ownerId, command);

        // Assert
        assertThat(result).isNotNull();
        verify(ownerGateway, times(1)).getOwnerById(ownerId);
        verify(ownerCredentialsGateway, times(1)).updatePasswordById(eq(ownerId), any(OwnerUpdatePasswordCommand.class));
    }

    @Test
    @DisplayName("Should throw exception when owner not found")
    void shouldThrowExceptionWhenOwnerNotFound() {
        // Arrange
        Integer ownerId = 999;
        when(ownerGateway.getOwnerById(ownerId)).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(ownerId, command))
            .hasMessageContaining("não encontrado");
    }
}

