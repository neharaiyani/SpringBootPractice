package com.codingshots.rest_crud.dao;

import com.codingshots.rest_crud.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    // interface methods are by default 'public'
    List<Employee> findAll();

    Employee findById(int theId);

    // insert / update
    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
