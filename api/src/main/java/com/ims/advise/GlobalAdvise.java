package com.ims.advise;

import com.ims.dto.DErrorResponse;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalAdvise extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new DErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DErrorResponse> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new DErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = "Invalid Content Provided. ";
        message += ex.getAllErrors().stream().findFirst().get().getDefaultMessage();
        return new ResponseEntity<>(new DErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", message), HttpStatus.BAD_REQUEST);
    }
}
