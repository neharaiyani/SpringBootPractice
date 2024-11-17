package com.codingshots.springmvc.validation_demo;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // Add support for @InitBinder (pre-processor) and
    // StringTrimmerEditor (to trim / convert empty strings to null value, remove leading and trailing whitespaces)
    // here, for empty lastName in form
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model theModel){

        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    // @Valid - Tell Spring MVC to perform validation (on the @ModelAttribute model)
    // BindingResult - The results of validation
    // NOTE: BindingResult must come immediately after @ModelAttribute

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult){

        if(theBindingResult.hasErrors()){
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}
