package com.codingshots.rest_crud.rest;

import com.codingshots.rest_crud.entity.Employee;
import com.codingshots.rest_crud.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

//    private EmployeeDAO employeeDAO;
    private EmployeeService employeeService;

    // quick and dirty : inject EmployeeDAO directly (Refactor Later)
//    public EmployeeRestController(EmployeeDAO theEmployeeDAO){
//        this.employeeDAO = theEmployeeDAO;
//    }

    // Refactor Code
    public EmployeeRestController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    // expose "/employees", and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        // delegate call to service layer
        Employee theEmployee = employeeService.findById(employeeId);

        // check for errors
        if(theEmployee == null){
            throw new RuntimeException("Employee Id not found : " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // In case, "id" is passed in JSON data, set it to 0
        // To force an "insert" of a new entry, instead of update
        theEmployee.setId(0);

        // save new employee to db
        Employee dbEmployee = employeeService.save(theEmployee);

        // return updated id's employee
        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        // update in db
        Employee dbEmployee = employeeService.save(theEmployee);

        // return updated employee
        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        // get employee from db
        Employee theEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if(theEmployee == null){
            throw new RuntimeException("Employee Id not found : " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted Employee Id : " + employeeId;
    }
}
