package br.com.petcolumbia.api_pet_columbia.core.application.command.owner;

public record OwnerUpdatePasswordCommand(
        String newPassword,
        String currentPassword
) {
}
