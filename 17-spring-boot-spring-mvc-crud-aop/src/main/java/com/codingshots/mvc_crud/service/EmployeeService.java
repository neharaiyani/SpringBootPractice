package com.codingshots.mvc_crud.service;

import com.codingshots.mvc_crud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    // insert / update
    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
