package dev.thesarfo.webfluxdemo.exceptionHandler;

import dev.thesarfo.webfluxdemo.dto.InputFailedValidationResponse;
import dev.thesarfo.webfluxdemo.exceptions.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex){
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
