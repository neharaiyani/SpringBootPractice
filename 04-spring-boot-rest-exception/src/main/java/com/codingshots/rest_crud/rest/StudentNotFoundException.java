package com.codingshots.rest_crud.rest;

public class StudentNotFoundException extends RuntimeException{

    // all the constructors generated from Superclass syntax (right click -> generate)
    public StudentNotFoundException(String message) {
        // call super class constructor
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
