package org.example.controller;

import org.example.exception.DBConnectionError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {DBConnectionError.class})
    public ResponseEntity<String> handleDBConnectionException(DBConnectionError ex) {
        return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"Temporary Error, Try again in sometime\"}");
    }
}
