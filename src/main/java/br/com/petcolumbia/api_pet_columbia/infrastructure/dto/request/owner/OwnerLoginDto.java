package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class OwnerLoginDto {
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
}
