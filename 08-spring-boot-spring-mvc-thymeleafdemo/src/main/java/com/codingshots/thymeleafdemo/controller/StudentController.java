package com.codingshots.thymeleafdemo.controller;

import com.codingshots.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    // import countries data from application.properties, add to the model in "/showForm"
    @Value("${countries}")
    private List<String> countries;

    // import languages data from application.properties, add to the model in "/showForm"
    @Value("${languages}")
    private List<String> languages;

    // import systems data from application.properties, add to the model in "/showForm"
    @Value("${systems}")
    private List<String> systems;

    // show student
    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){
        // create a student object
        Student theStudent = new Student();

        /*** Before you show the form, you must add a "model" attribute (here, empty),
         * This is a "bean" that will "hold form data" for the data binding ***/

        // add student abject to the model
        theModel.addAttribute("student", theStudent);

        // add the list of countries to the model
        theModel.addAttribute("countries", countries);

        // add the list of languages to the model
        theModel.addAttribute("languages", languages);

        // add the list of systems to the model
        theModel.addAttribute("systems", systems);

        return "student-form";
    }

    // process student
    // @ModelAttribute: used in form submissions where multiple related parameters need to be bound to an object
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent){

        System.out.println("The Student : " + theStudent.getFirstName() + " " + theStudent.getLastName());

        return "student-confirmation";
    }
}
