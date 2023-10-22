package com.example.retourexperience.exceptions;

import java.io.Serial;

public class UserServiceException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = -3252142269389878856L;

    public UserServiceException(String message) {
        super(message);
    }
}
