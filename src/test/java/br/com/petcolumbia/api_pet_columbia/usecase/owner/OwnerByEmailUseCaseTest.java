package br.com.petcolumbia.api_pet_columbia.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.OwnerByEmailUseCase;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OwnerByEmailUseCase - Unit Tests")
class OwnerByEmailUseCaseTest {

    @Mock
    private OwnerGateway ownerGateway;

    @InjectMocks
    private OwnerByEmailUseCase useCase;

    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
            "$2a$10$encodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should find owner by email successfully")
    void shouldFindOwnerByEmailSuccessfully() {
        // Arrange
        when(ownerGateway.getOwnerByEmail("joao@email.com")).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute("joao@email.com");

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("joao@email.com");
        verify(ownerGateway, times(1)).getOwnerByEmail("joao@email.com");
    }

    @Test
    @DisplayName("Should throw exception when owner not found by email")
    void shouldThrowExceptionWhenOwnerNotFoundByEmail() {
        // Arrange
        when(ownerGateway.getOwnerByEmail("invalid@email.com"))
            .thenThrow(new EntityNotFoundException("Owner not found"));

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute("invalid@email.com"))
            .isInstanceOf(EntityNotFoundException.class);
    }
}

