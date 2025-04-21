package br.com.petcolumbia.api_pet_columbia.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class OwnerUpdateDto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "O número de telefone é obrigatório")
    @Size(min = 11, max = 11, message = "O número de telefone deve conter 11 caracteres, sem formatação")
    private String phoneNumber;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 9 caracteres (formato 00000-000)")
    private String cep;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String neighborhood;

    @NotBlank(message = "A rua é obrigatória")
    @Size(max = 100, message = "A rua deve ter no máximo 100 caracteres")
    private String street;

    @NotBlank(message = "O número é obrigatório")
    @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres")
    private String number;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
    private String complement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }
}
