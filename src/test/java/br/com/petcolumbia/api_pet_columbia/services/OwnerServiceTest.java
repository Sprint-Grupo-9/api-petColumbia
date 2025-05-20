package br.com.petcolumbia.api_pet_columbia.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.EntityNotFoundException;

class OwnerServiceTest {

    private OwnerRepository ownerRepository;
    private OwnerService ownerService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        ownerRepository = mock(OwnerRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        ownerService = new OwnerService(ownerRepository, passwordEncoder);
    }

    /**
     * Verifica que, durante a criação (id = null), o método retorna false
     * quando não há duplicidade de e-mail, CPF ou telefone.
     */
    @Test
    void testIsDuplicateFields_Create_WhenNoDuplicates() {
        when(ownerRepository.existsByEmail("email@test.com")).thenReturn(false);
        when(ownerRepository.existsByCpf("12345678901")).thenReturn(false);
        when(ownerRepository.existsByPhoneNumber("999999999")).thenReturn(false);

        boolean result = ownerService.isDuplicateFields("email@test.com", "12345678901", "999999999", null);
        assertFalse(result);
    }

    /**
     * Garante que, durante a criação (id = null), o método retorna true
     * quando já existe um proprietário com o mesmo e-mail.
     */
    @Test
    void testIsDuplicateFields_Create_WhenEmailExists() {
        when(ownerRepository.existsByEmail("email@test.com")).thenReturn(true);

        boolean result = ownerService.isDuplicateFields("email@test.com", "12345678901", "999999999", null);
        assertTrue(result);
    }

    /**
     * Verifica que, durante a atualização (id ≠ null), o método retorna false
     * quando os campos não estão duplicados em outros registros.
     */
    @Test
    void testIsDuplicateFields_Update_WhenNoDuplicates() {
        Integer id = 1;

        when(ownerRepository.existsByEmailAndIdNot("email@test.com", id)).thenReturn(false);
        when(ownerRepository.existsByCpfAndIdNot("12345678901", id)).thenReturn(false);
        when(ownerRepository.existsByPhoneNumberAndIdNot("999999999", id)).thenReturn(false);

        boolean result = ownerService.isDuplicateFields("email@test.com", "12345678901", "999999999", id);
        assertFalse(result);
    }

    /**
     * Garante que, durante a atualização (id ≠ null), o método retorna true
     * quando o CPF já está em uso por outro proprietário.
     */
    @Test
    void testIsDuplicateFields_Update_WhenCpfExists() {
        Integer id = 1;

        when(ownerRepository.existsByEmailAndIdNot("email@test.com", id)).thenReturn(false);
        when(ownerRepository.existsByCpfAndIdNot("12345678901", id)).thenReturn(true);
        when(ownerRepository.existsByPhoneNumberAndIdNot("999999999", id)).thenReturn(false);

        boolean result = ownerService.isDuplicateFields("email@test.com", "12345678901", "999999999", id);
        assertTrue(result);
    }

    /**
     * Verifica que a senha é atualizada corretamente quando o ID do usuário é válido
     * e a senha atual informada corresponde à armazenada.
     */
    @Test
    void testUpdatePasswordById_Success() {
        Integer ownerId = 1;

        OwnerModel owner = new OwnerModel();
        owner.setId(ownerId);
        owner.setPassword("encodedOldPassword");

        OwnerUpdatePasswordDto dto = new OwnerUpdatePasswordDto();
        dto.setCurrentPassword("plainOldPassword");
        dto.setNewPassword("newSecurePassword");

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        when(passwordEncoder.matches("plainOldPassword", "encodedOldPassword")).thenReturn(true);
        when(passwordEncoder.encode("newSecurePassword")).thenReturn("encodedNewPassword");

        OwnerModel result = ownerService.updatePasswordById(ownerId, dto);

        assertEquals("encodedNewPassword", result.getPassword());
        assertNotNull(result.getLastUpdate());
    }

    /**
     * Garante que uma exceção {@code EntityNotFoundException} é lançada
     * ao tentar atualizar a senha de um usuário que não existe.
     */
    @Test
    void testUpdatePasswordById_OwnerNotFound() {
        Integer ownerId = 99;
        OwnerUpdatePasswordDto dto = new OwnerUpdatePasswordDto();

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            ownerService.updatePasswordById(ownerId, dto);
        });

        assertEquals("Usuário não encontrado", ex.getMessage());
    }

    /**
     * Verifica que uma exceção {@code EntityUnauthorizedException} é lançada
     * quando a senha atual informada não corresponde à armazenada.
     */
    @Test
    void testUpdatePasswordById_IncorrectPassword() {
        Integer ownerId = 1;

        OwnerModel owner = new OwnerModel();
        owner.setId(ownerId);
        owner.setPassword("encodedOldPassword");

        OwnerUpdatePasswordDto dto = new OwnerUpdatePasswordDto();
        dto.setCurrentPassword("wrongPassword");
        dto.setNewPassword("newPassword");

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        when(passwordEncoder.matches("wrongPassword", "encodedOldPassword")).thenReturn(false);

        EntityUnauthorizedException ex = assertThrows(EntityUnauthorizedException.class, () -> {
            ownerService.updatePasswordById(ownerId, dto);
        });

        assertEquals("Senha atual incorreta", ex.getMessage());
    }

    // Mock classes e service apenas para teste

    interface OwnerRepository {
        boolean existsByEmail(String email);
        boolean existsByCpf(String cpf);
        boolean existsByPhoneNumber(String phoneNumber);
        boolean existsByEmailAndIdNot(String email, Integer id);
        boolean existsByCpfAndIdNot(String cpf, Integer id);
        boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
        Optional<OwnerModel> findById(Integer id);
    }

    static class OwnerService {
        private final OwnerRepository ownerRepository;
        private final PasswordEncoder passwordEncoder;

        public OwnerService(OwnerRepository ownerRepository) {
            this(ownerRepository, null);
        }

        public OwnerService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
            this.ownerRepository = ownerRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public boolean isDuplicateFields(String email, String cpf, String phoneNumber, Integer id) {
            if (id == null)
                return ownerRepository.existsByEmail(email)
                        || ownerRepository.existsByCpf(cpf)
                        || ownerRepository.existsByPhoneNumber(phoneNumber);

            return ownerRepository.existsByEmailAndIdNot(email, id)
                    || ownerRepository.existsByCpfAndIdNot(cpf, id)
                    || ownerRepository.existsByPhoneNumberAndIdNot(phoneNumber, id);
        }

        public OwnerModel updatePasswordById(Integer id, OwnerUpdatePasswordDto dto) {
            OwnerModel owner = ownerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

            if (!passwordEncoder.matches(dto.getCurrentPassword(), owner.getPassword()))
                throw new EntityUnauthorizedException("Senha atual incorreta");

            owner.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            owner.setLastUpdate(LocalDateTime.now());

            return owner;
        }
    }

    static class OwnerModel {
        private Integer id;
        private String password;
        private LocalDateTime lastUpdate;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public LocalDateTime getLastUpdate() { return lastUpdate; }
        public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
    }

    static class OwnerUpdatePasswordDto {
        private String currentPassword;
        private String newPassword;

        public String getCurrentPassword() { return currentPassword; }
        public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }

    static class EntityUnauthorizedException extends RuntimeException {
        public EntityUnauthorizedException(String message) {
            super(message);
        }
    }
}
