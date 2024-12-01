package be.ucll.backend2.controller;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlers {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Map<String, String> handleValidationException(HandlerMethodValidationException ex) {
        final Map<String, String> errors = new HashMap<>();
        for (final var parameterValidationResult : ex.getParameterValidationResults()) {
            for (final var error : parameterValidationResult.getResolvableErrors()) {
                final var parameter =
                        ((MessageSourceResolvable) Objects.requireNonNull(error.getArguments())[0]).getDefaultMessage();
                final var errorMessage = error.getDefaultMessage();
                errors.put(parameter, errorMessage);
            }
        }
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            final var fieldName = error.getField();
            final var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
