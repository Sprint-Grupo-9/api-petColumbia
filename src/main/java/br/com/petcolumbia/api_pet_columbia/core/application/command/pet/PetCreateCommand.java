package br.com.petcolumbia.api_pet_columbia.core.application.command.pet;

public record PetCreateCommand(
        String name,
        String size,
        String species,
        String breed,
        String coat,
        Integer age,
        String sex
) {
}