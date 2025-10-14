package com.desafios.apitodolist.domain.exception;

import com.desafios.apitodolist.domain.exception.task.InvalidTaskDataException;
import com.desafios.apitodolist.domain.exception.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception exception){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                methodArgumentTypeMismatchException.getValue(),
                methodArgumentTypeMismatchException.getName(),
                methodArgumentTypeMismatchException.getRequiredType() != null ? methodArgumentTypeMismatchException.getRequiredType().getSimpleName() : "unknown");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), methodArgumentNotValidException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
