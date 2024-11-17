package com.codingshots.jpa_advanced.dao;

import com.codingshots.jpa_advanced.entity.Instructor;
import com.codingshots.jpa_advanced.entity.InstructorDetail;

public interface AppDAO {

    // save instructor
    void save(Instructor theInstructor);

    // find instructor by ID
    Instructor findInstructorByID(int theId);

    // delete instructor by ID
    void deleteInstructorById(int theId);

    // find instructorDetail by ID (bidirectional mapping)
    InstructorDetail findInstructorDetailById(int theId);

    // cascade delete - delete instructorDetail by ID
    void deleteInstructorDetailById(int theId);
}
