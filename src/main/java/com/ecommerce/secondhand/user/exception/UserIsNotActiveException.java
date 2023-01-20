package com.ecommerce.secondhand.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserIsNotActiveException extends RuntimeException{

    public static final String message = "The user wanted update is not active";

    public UserIsNotActiveException(String message) {
        super(message);
    }
}
