package com.codingshots.mvc_crud.dao;

import com.codingshots.mvc_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// pass entity type, primary key type
// @RepositoryRestResource(path="members")  // REST Resource path => instead of "/employees", use "/members"
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // no need to write any implementation code!!!

    // explore more advanced queries here (Spring Data JPA docs)

    // add a method to sort by last name
    // Spring Data JPA will parse the method name, looks for specific format and pattern, creates appropriate query...behind the scenes
    public List<Employee> findAllByOrderByLastNameAsc();
}
