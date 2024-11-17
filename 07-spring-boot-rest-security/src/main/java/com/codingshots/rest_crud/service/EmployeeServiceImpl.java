package com.codingshots.rest_crud.service;

import com.codingshots.rest_crud.dao.EmployeeRepository;
import com.codingshots.rest_crud.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

//    private EmployeeDAO employeeDAO;

    // Refactor Code for "Spring Data JPA"
    private EmployeeRepository employeeRepository;

    // constructor injection
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
//        this.employeeDAO = theEmployeeDAO;
        this.employeeRepository = theEmployeeRepository;
    }

    // delegate calls to DAO layer

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Employee theEmployee = null;

        // different pattern instead of having to check for nulls.
        Optional<Employee> employee = employeeRepository.findById(theId);

        if(employee.isPresent()){
            theEmployee = employee.get();
        }
        else{
            // we didn't find the employee
            throw new RuntimeException("Did not find Employee Id : " + theId);
        }

        return theEmployee;
    }

    // Apply @Transactional (use only when db is updated) at the service layer (Best Practice)
    // Remove @Transactional, as JpaRepository provides this functionality
//    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

//    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
