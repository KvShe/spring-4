package ru.kvshe.homework.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kvshe.homework.exception.NotFoundException;
import ru.kvshe.homework.exception.ReAddingAUserToProjectException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ReAddingAUserToProjectException.class)
    public ResponseEntity<?> handleException(ReAddingAUserToProjectException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
        // todo изменить на страницу с ошибкой
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleException(NoSuchElementException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
        // todo изменить на страницу с ошибкой
    }
}
