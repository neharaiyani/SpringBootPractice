package com.codingshots.rest_crud.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   // specify global exception handlers for multiple controllers
public class StudentRestExceptionHandler {

    // add exception handling code here

    // Add an exception handler using @ExceptionHandler

    // returns Type of the Response Body: StudentErrorRespond, Exception to handle / catch: StudentNotFoundException
    @ExceptionHandler   // exception handler method
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add another exception handler ... to catch any exception (catch all, generic)
    // e.g., String passed as an Id, instead of a number
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
