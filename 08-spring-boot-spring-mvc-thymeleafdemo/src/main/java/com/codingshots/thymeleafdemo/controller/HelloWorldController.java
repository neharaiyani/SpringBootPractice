package com.codingshots.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

    // a new controller method to show initial HTML form
//    @RequestMapping("/showForm")
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // need a controller method to read form data and add data to the model
    @RequestMapping("/processFormV2")
    public String processFormVersionTwo(HttpServletRequest request, Model model){

        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

    // 3
    @PostMapping("/processFormV3")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model){

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey my friend from V3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
}
