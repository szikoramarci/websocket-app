package com.webness.websocket_app.exception;

import java.util.stream.Collectors;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.webness.websocket_app.dto.ErrorResponse;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class WebSocketExceptionHandler {

    @MessageExceptionHandler(MethodArgumentNotValidException.class)
    @SendToUser("/topic/errors")
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        log.warn("Validation error: " + errorMessage);

        return new ErrorResponse("validation_error", "Validation error", errorMessage);
    }

    @MessageExceptionHandler(ConstraintViolationException.class)
    @SendToUser("/topic/errors")
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining("; "));

        log.error("Constraint error: " + errorMessage);

        return new ErrorResponse("constraint_error", "Constraint error", errorMessage);
    }

    @MessageExceptionHandler(Exception.class)
    @SendToUser("/topic/errors")
    public ErrorResponse handleGenericException(Exception ex) {
        log.error("Unkown error: " + ex.getMessage());

        return new ErrorResponse("unkown_error", "Unkown error", ex.getMessage());
    }
}