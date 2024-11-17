package com.codingshots.rest_crud.dao;

import com.codingshots.rest_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// pass entity type, primary key type
// @RepositoryRestResource(path="members")  // REST Resource path => instead of "/employees", use "/members"
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // no need to write any implementation code!!!

    // explore more advanced queries here
}
