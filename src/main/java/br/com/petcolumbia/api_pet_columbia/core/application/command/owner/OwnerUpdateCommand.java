package br.com.petcolumbia.api_pet_columbia.core.application.command.owner;

public record OwnerUpdateCommand (
    String phoneNumber,
    String email,
    String cep,
    String neighborhood,
    String street,
    String number,
    String complement
){
}
