package com.codingshots.jpa_advanced.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // *** set up mapping to InstructorDetail entity

    // create constructors

    // generate getter / setter methods

    // generate toString() method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")  // "instructor_detail_id" is a column in "instructor" table
    private InstructorDetail instructorDetail;

    /*** New field, list of Course, One-to-Many, Bidirectional, No Cascade Delete ***/
    @OneToMany(mappedBy = "instructor",
               //fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})     // "instructor" field of Course class (Java)
    private List<Course> courses;

    public Instructor(){}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    /* new getter/setter */
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /*** add convenient methods for bidirectional relationship, as we have to set link between 2 Java Objects ***/
    public void add(Course tempCourse){
        // initialize courses list, if not already
        if(courses == null){
            courses = new ArrayList<>();
        }

        // add new course to the courses list of this instructor
        courses.add(tempCourse);

        // 2 way link - also, set this instructor as the "given course's instructor" (associate the objects)
        tempCourse.setInstructor(this);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
