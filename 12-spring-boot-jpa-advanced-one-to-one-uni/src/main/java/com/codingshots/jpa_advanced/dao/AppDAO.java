package com.codingshots.jpa_advanced.dao;

import com.codingshots.jpa_advanced.entity.Instructor;

public interface AppDAO {

    // save instructor
    void save(Instructor theInstructor);

    // find instructor by ID
    Instructor findInstructorByID(int theId);

    // delete instructor by ID
    void deleteInstructorById(int theId);
}
