package br.com.petcolumbia.api_pet_columbia.core.application.command.owner;

public record OwnerAutenticateCommand(
        String email,
        String password
) {
}
