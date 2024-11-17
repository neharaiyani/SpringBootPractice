package com.codingshots.rest_crud.rest;

import com.codingshots.rest_crud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ... (called only once, when this bean is initialized)
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        // We'll hard code data for now … can add DB later …

        // Jackson will convert List<Student> to JSON array
        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return student at index

    // {studentId} :- path variable
    // {studentId} and @PathVariable's parameter name "studentId" should match
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // just index into the list for now

        // check the studentId against list size, if yes => throw custom exception
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    /***** @ Exception Handler methods, moved to Global exception handler: StudentRestExceptionHandler.java *****/
//    // Add an exception handler using @ExceptionHandler
//
//    // returns Type of the Response Body: StudentErrorRespond, Exception to handle / catch: StudentNotFoundException
//    @ExceptionHandler   // exception handler method
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
//        // create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // return ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    // Add another exception handler ... to catch any exception (catch all, generic)
//    // e.g., String passed as an Id, instead of a number
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
//        // create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // return ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
}
