package br.com.petcolumbia.api_pet_columbia.core.application.exception;

public class EntityConflictException extends RuntimeException{
    public EntityConflictException(String message) {
        super(message);
    }
}
