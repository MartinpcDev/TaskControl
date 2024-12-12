package com.taskcontrol.api.controller;

import com.taskcontrol.api.dto.response.ExceptionResponse;
import com.taskcontrol.api.exceptions.TaskNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleTaskNotFound(TaskNotFoundException e) {
    ExceptionResponse response = new ExceptionResponse(
        "Recurso no encontrado",
        e.getMessage(),
        null
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleNotValidation(MethodArgumentNotValidException e) {
    List<ObjectError> errors = e.getAllErrors();
    List<String> details = errors.stream()
        .map(error -> {
          if (error instanceof FieldError fieldError) {
            return fieldError.getField() + " : " + fieldError.getDefaultMessage();
          }
          return error.getDefaultMessage();
        }).toList();

    ExceptionResponse response = new ExceptionResponse(
        "Error de Validacion",
        null,
        details
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e) {
    ExceptionResponse response = new ExceptionResponse(
        "Error inesperado",
        e.getMessage(),
        null
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
