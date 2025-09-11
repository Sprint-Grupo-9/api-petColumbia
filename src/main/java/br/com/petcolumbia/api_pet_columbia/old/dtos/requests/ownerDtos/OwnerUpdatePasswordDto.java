package br.com.petcolumbia.api_pet_columbia.old.dtos.requests.ownerDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OwnerUpdatePasswordDto {
    @NotBlank(message = "A nova senha é obrigatória")
    @Size(min = 6, max = 255, message = "A senha deve ter entre 6 e 255 caracteres")
    private String newPassword;
    @NotBlank(message = "A senha atual é obrigatória")
    @Size(min = 6, max = 255, message = "A senha deve ter entre 6 e 255 caracteres")
    private String currentPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }


}
