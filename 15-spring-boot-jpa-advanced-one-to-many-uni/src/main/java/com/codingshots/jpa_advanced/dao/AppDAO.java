package com.codingshots.jpa_advanced.dao;

import com.codingshots.jpa_advanced.entity.Course;
import com.codingshots.jpa_advanced.entity.Instructor;
import com.codingshots.jpa_advanced.entity.InstructorDetail;

import java.util.List;

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

    // Lazy load courses for Instructor
    List<Course> findCoursesByInstructorId(int theId);

    // JOIN FETCH
    Instructor findInstructorByJoinFetch(int theId);

    // update instructor
    void updateInstructor(Instructor theInstructor);

    // we didn't add a functionality to find the course by id (like instructor above)
    Course findCourseById(int theId);

    // update course
    void updateCourse(Course theCourse);

    // delete course by id
    void deleteCourseById(int theId);

    /* NEW: Course -> Review methods */

    // save course
    void saveCourse(Course theCourse);

    // retrieve course and reviews
    Course findCourseAndReviewsByCourseId(int theId);
}
