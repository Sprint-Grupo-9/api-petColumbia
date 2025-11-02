 package br.com.petcolumbia.api_pet_columbia.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.core.application.service.OwnerValidationService;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.CreateOwnerUseCase;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateOwnerUseCase - Unit Tests")
class CreateOwnerUseCaseTest {

    @Mock
    private OwnerGateway ownerGateway;

    @Mock
    private OwnerValidationService validationService;

    @InjectMocks
    private CreateOwnerUseCase useCase;

    private OwnerCreateCommand validCommand;
    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        validCommand = new OwnerCreateCommand(
            "João Silva", "12345678901", "11987654321", "joao@email.com",
            "Senha123.", "01234567", "Centro", "Rua Teste", "123", "Apto 101"
        );

        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
            "$2a$10$encodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should create owner successfully")
    void shouldCreateOwnerSuccessfully() {
        // Arrange
        when(validationService.checkDuplicateFieldsOnCreate(anyString(), anyString(), anyString()))
            .thenReturn(false);
        when(ownerGateway.create(any(Owner.class))).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(validCommand);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("João Silva");
        assertThat(result.getEmail()).isEqualTo("joao@email.com");
        verify(ownerGateway, times(1)).create(any(Owner.class));
    }

    @Test
    @DisplayName("Should throw exception when duplicate fields exist")
    void shouldThrowExceptionWhenDuplicateFieldsExist() {
        // Arrange
        when(validationService.checkDuplicateFieldsOnCreate(anyString(), anyString(), anyString()))
            .thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(validCommand))
            .isInstanceOf(EntityConflictException.class)
            .hasMessageContaining("Já existe um usuário");
    }

    @Test
    @DisplayName("Should validate CPF format")
    void shouldValidateCpfFormat() {
        // Arrange
        when(validationService.checkDuplicateFieldsOnCreate(anyString(), anyString(), anyString()))
            .thenReturn(false);
        when(ownerGateway.create(any(Owner.class))).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(validCommand);

        // Assert
        assertThat(result.getPersonalInfo().getCpf()).matches("\\d{11}");
    }

    @Test
    @DisplayName("Should create owner as non-admin by default")
    void shouldCreateOwnerAsNonAdminByDefault() {
        // Arrange
        when(validationService.checkDuplicateFieldsOnCreate(anyString(), anyString(), anyString()))
            .thenReturn(false);
        when(ownerGateway.create(any(Owner.class))).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(validCommand);

        // Assert
        assertThat(result.getAdm()).isFalse();
    }
}

