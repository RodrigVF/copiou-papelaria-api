package com.rodrigvf.copiou_papelaria_api.config;

import com.rodrigvf.copiou_papelaria_api.exception.UnauthorizedException;
import com.rodrigvf.copiou_papelaria_api.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUsernameOrPasswordInvalidException(UsernameOrPasswordInvalidException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
                errors.put(((FieldError) error).getField(), error.getDefaultMessage())
        );
        return errors;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
