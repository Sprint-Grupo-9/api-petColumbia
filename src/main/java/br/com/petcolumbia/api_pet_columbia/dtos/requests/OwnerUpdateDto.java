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
    @Size(min = 15, max = 15, message = "O número de telefone deve conter exatamente 15 caracteres (com DDD e máscara)")
    private String phoneNumber;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 9, max = 9, message = "O CEP deve ter exatamente 9 caracteres (formato 00000-000)")
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

    public @NotBlank(message = "O nome é obrigatório") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "O nome é obrigatório") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") String name) {
        this.name = name;
    }

    public @NotBlank(message = "O CPF é obrigatório") @CPF(message = "CPF inválido") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "O CPF é obrigatório") @CPF(message = "CPF inválido") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "O número de telefone é obrigatório") @Size(min = 15, max = 15, message = "O número de telefone deve conter exatamente 15 caracteres (com DDD e máscara)") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "O número de telefone é obrigatório") @Size(min = 15, max = 15, message = "O número de telefone deve conter exatamente 15 caracteres (com DDD e máscara)") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "O e-mail é obrigatório") @Email(message = "Formato de e-mail inválido") @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O e-mail é obrigatório") @Email(message = "Formato de e-mail inválido") @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres") String email) {
        this.email = email;
    }

    public @NotBlank(message = "O CEP é obrigatório") @Size(min = 9, max = 9, message = "O CEP deve ter exatamente 9 caracteres (formato 00000-000)") String getCep() {
        return cep;
    }

    public void setCep(@NotBlank(message = "O CEP é obrigatório") @Size(min = 9, max = 9, message = "O CEP deve ter exatamente 9 caracteres (formato 00000-000)") String cep) {
        this.cep = cep;
    }

    public @NotBlank(message = "O bairro é obrigatório") @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres") String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(@NotBlank(message = "O bairro é obrigatório") @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres") String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public @NotBlank(message = "A rua é obrigatória") @Size(max = 100, message = "A rua deve ter no máximo 100 caracteres") String getStreet() {
        return street;
    }

    public void setStreet(@NotBlank(message = "A rua é obrigatória") @Size(max = 100, message = "A rua deve ter no máximo 100 caracteres") String street) {
        this.street = street;
    }

    public @NotBlank(message = "O número é obrigatório") @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres") String getNumber() {
        return number;
    }

    public void setNumber(@NotBlank(message = "O número é obrigatório") @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres") String number) {
        this.number = number;
    }

    public @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres") String getComplement() {
        return complement;
    }

    public void setComplement(@Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres") String complement) {
        this.complement = complement;
    }
}
