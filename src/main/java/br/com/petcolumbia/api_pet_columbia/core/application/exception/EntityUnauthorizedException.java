package br.com.petcolumbia.api_pet_columbia.core.application.exception;

public class EntityUnauthorizedException extends RuntimeException {
    public EntityUnauthorizedException(String message){
        super(message);
    }
}
