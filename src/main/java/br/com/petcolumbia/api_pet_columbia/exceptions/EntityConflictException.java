package br.com.petcolumbia.api_pet_columbia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityConflictException extends RuntimeException{
    public EntityConflictException(String message) {
        super(message);
    }
}
