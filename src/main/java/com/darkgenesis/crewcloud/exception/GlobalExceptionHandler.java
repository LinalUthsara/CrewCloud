package com.darkgenesis.crewcloud.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // @ExceptionHandler(DepartmentNotFoundException.class)
    // public ResponseEntity<String> handleDepartmentNotFoundException (DepartmentNotFoundException departmentNotFoundException){
    //     return new ResponseEntity<>(departmentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    // }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException (DepartmentNotFoundException departmentNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departmentNotFoundException.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException (DataIntegrityViolationException dataIntegrityViolationException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save department: " + dataIntegrityViolationException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException (Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + exception.getMessage());
    }


    @ExceptionHandler(WorkScheduleNotFoundException.class)
    public ResponseEntity<String> WorkScheduleNotFoundException (WorkScheduleNotFoundException workScheduleNotFoundException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + workScheduleNotFoundException.getMessage());
    }
}
