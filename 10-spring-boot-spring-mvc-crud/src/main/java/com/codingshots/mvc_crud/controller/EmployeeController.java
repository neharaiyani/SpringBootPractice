package com.codingshots.mvc_crud.controller;

import com.codingshots.mvc_crud.entity.Employee;
import com.codingshots.mvc_crud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // @Autowired is optional
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        // get the employee from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    // form for save employee
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        model.addAttribute("employee", theEmployee);

        return "employees/employees-form";
    }

    // save employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions ("Post/Redirect/Get" pattern)
        // It is used to prevent the "resubmission of a form" caused by reloading the same web page after submitting the form
        return "redirect:/employees/list";
    }

    // form for update employee
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model){

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to prepopulate the form
        model.addAttribute("employee", theEmployee);

        // reuse the same form for save employee
        return "employees/employees-form";
    }

    // delete employee
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to the /employees/list
        return "redirect:/employees/list";
    }
}
