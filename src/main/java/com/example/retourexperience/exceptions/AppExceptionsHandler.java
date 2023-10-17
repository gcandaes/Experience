package com.example.retourexperience.exceptions;

import com.example.retourexperience.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        String errorMessageDescrption = ex.getLocalizedMessage();
        if (errorMessageDescrption == null) errorMessageDescrption = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescrption);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

/*    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        String errorMessageDescrption = ex.getLocalizedMessage();
        if (errorMessageDescrption == null) errorMessageDescrption = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescrption);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
