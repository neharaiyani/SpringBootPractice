package com.codingshots.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    // create a mapping for "/hello"
    // use any Method Name
    @GetMapping("/hello")
    public String sayHello(Model model){
        // always add a "Model" in Controller Method to carry data for View
        model.addAttribute("theDate", java.time.LocalDateTime.now());

        // src/main/resources/templates/helloworld.html
        return "helloworld";
    }
}
