package com.codingshots.rest_crud.dao;

import com.codingshots.rest_crud.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // define field for Entity Manager
    private EntityManager entityManager;

    // set up Constructor Injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // get employee from db
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // return employee
        return theEmployee;
    }

    // insert / update: if id == 0 then save/insert, else update
    @Override
    public Employee save(Employee theEmployee) {
        // save given employee to db
        Employee dbEmployee = entityManager.merge(theEmployee);

        // return the dbEmployee (the fresh version, also updated "id" in case of insert)
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // get employee from db
        Employee theEmployee = entityManager.find(Employee.class, theId);

        entityManager.remove(theEmployee);
    }
}
