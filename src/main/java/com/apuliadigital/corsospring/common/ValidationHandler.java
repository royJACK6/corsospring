package com.apuliadigital.corsospring.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidation (MethodArgumentNotValidException ex){
        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err ->err.getField()+ ":" + err.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
