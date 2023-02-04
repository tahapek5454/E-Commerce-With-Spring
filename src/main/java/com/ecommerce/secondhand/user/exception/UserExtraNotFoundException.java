package com.ecommerce.secondhand.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserExtraNotFoundException extends RuntimeException{

    public UserExtraNotFoundException(String message) {
        super(message);
    }
}
