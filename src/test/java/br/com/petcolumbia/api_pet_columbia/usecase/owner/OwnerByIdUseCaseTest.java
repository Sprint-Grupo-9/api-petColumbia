package br.com.petcolumbia.api_pet_columbia.usecase.owner;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.OwnerByIdUseCase;
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
@DisplayName("OwnerByIdUseCase - Unit Tests")
class OwnerByIdUseCaseTest {

    @Mock
    private OwnerGateway ownerGateway;

    @InjectMocks
    private OwnerByIdUseCase useCase;

    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        PersonalInfo personalInfo = new PersonalInfo("12345678901", "11987654321");
        Address address = new Address("01234567", "Centro", "Rua Teste", "123", "Apto 101");
        mockOwner = new Owner(1, "João Silva", personalInfo, "joao@email.com",
            "$2a$10$encodedPassword", address, false, null);
    }

    @Test
    @DisplayName("Should find owner by ID successfully")
    void shouldFindOwnerByIdSuccessfully() {
        // Arrange
        when(ownerGateway.getOwnerById(1)).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("João Silva");
        verify(ownerGateway, times(1)).getOwnerById(1);
    }

    @Test
    @DisplayName("Should throw exception when owner not found")
    void shouldThrowExceptionWhenOwnerNotFound() {
        // Arrange
        when(ownerGateway.getOwnerById(999)).thenThrow(new EntityNotFoundException("Owner not found"));

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(999))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessageContaining("not found");
    }

    @Test
    @DisplayName("Should return owner with all required fields")
    void shouldReturnOwnerWithAllRequiredFields() {
        // Arrange
        when(ownerGateway.getOwnerById(1)).thenReturn(mockOwner);

        // Act
        Owner result = useCase.execute(1);

        // Assert
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isNotNull();
        assertThat(result.getEmail()).isNotNull();
        assertThat(result.getPersonalInfo()).isNotNull();
        assertThat(result.getAdress()).isNotNull();
    }
}

