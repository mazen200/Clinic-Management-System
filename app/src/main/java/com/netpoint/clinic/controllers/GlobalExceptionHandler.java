package com.netpoint.clinic.controllers;

import com.netpoint.clinic.dtos.ErrorDto;
import com.netpoint.clinic.exceptions.ResourceExistException;
import com.netpoint.clinic.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(
            ResourceNotFoundException ex
    , HttpServletRequest request) {
        ErrorDto error = new ErrorDto();

        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(404);
        error.setTimestamp(Instant.now());
        error.setError("Resource Not Found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage()
                )
                .collect(Collectors.joining(", "));

        var error = new ErrorDto();

        error.setMessage(message);
        error.setPath(request.getRequestURI());
        error.setStatus(400);
        error.setTimestamp(Instant.now());
        error.setError("Validation Failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ErrorDto> handleResourceExistException
            (Exception ex, HttpServletRequest request){
            var error = new ErrorDto();

            error.setMessage(ex.getMessage());
            error.setPath(request.getRequestURI());
            error.setStatus(409);
            error.setTimestamp(Instant.now());
            error.setError("Resource Already Exists");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex, HttpServletRequest request) {

        log.error(ex.getMessage(), ex);

        var error = new ErrorDto();

        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(500);
        error.setTimestamp(Instant.now());
        error.setError("Internal Server Error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
