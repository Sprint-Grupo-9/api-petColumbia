package br.com.petcolumbia.api_pet_columbia.old.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EntityUnauthorizedException extends RuntimeException {
    public EntityUnauthorizedException(String message){
        super(message);
    }
}
