package com.mirandasidney.pdv.api.handler;

import com.mirandasidney.pdv.api.exception.ApiError;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        List<String> err = new ArrayList();
        err.add(ex.getMessage());

        return ResponseEntityBuilder.build(ApiError
                .builder()
                .message("Resource Not Found, check the documentation")
                .status(HttpStatus.BAD_REQUEST.value())
                .className(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .errors(err)
                .build());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        List<String> err = new ArrayList();
        err.add(ex.getMessage());

        return ResponseEntityBuilder.build(ApiError
                .builder()
                .message("Constraint Infringed, check the documentation")
                .status(HttpStatus.BAD_REQUEST.value())
                .className(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .errors(err)
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList();
        details.add(ex.getMessage());

        return ResponseEntityBuilder.build(ApiError
                .builder()
                .message("Malformed JSON request")
                .status(HttpStatus.BAD_REQUEST.value())
                .className(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .errors(details)
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntityBuilder.build(ApiError
                .builder()
                .message("Validation Errors")
                .status(HttpStatus.BAD_REQUEST.value())
                .className(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .errors(details)
                .build());
    }
}
