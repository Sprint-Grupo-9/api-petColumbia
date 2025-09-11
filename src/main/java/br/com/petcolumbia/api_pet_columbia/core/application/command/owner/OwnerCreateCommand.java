package br.com.petcolumbia.api_pet_columbia.core.application.command.owner;

public record OwnerCreateCommand(
        String name,
        String cpf,
        String phoneNumber,
        String email,
        String password,
        String cep,
        String neighborhood,
        String street,
        String number,
        String complement
) {
}
