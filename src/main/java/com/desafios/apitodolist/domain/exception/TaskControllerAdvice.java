package com.desafios.apitodolist.domain.exception;

import com.desafios.apitodolist.domain.exception.task.InvalidTaskDataException;
import com.desafios.apitodolist.domain.exception.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handleTaskNotFoundException(TaskNotFoundException taskNotFoundException){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), taskNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(InvalidTaskDataException.class)
    public ResponseEntity<ApiError> handleInvalidTaskDataException(InvalidTaskDataException invalidTaskDataException){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), invalidTaskDataException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
