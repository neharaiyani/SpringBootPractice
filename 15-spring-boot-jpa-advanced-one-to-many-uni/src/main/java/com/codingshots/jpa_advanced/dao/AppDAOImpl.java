package com.codingshots.jpa_advanced.dao;

import com.codingshots.jpa_advanced.entity.Course;
import com.codingshots.jpa_advanced.entity.Instructor;
import com.codingshots.jpa_advanced.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // if not specified, error (as this class will not be added in component scanning)
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject the entity manager using constructor injection
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        // this will also save the InstructorDetail object, bcz of CascadeType.ALL in Instructor class
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorByID(int theId) {
        // this will also retrieve the InstructorDetail object
        // bcz default behavior of @OneToOne fetch type is "Eager"
        return entityManager.find(Instructor.class, theId);
    }

    /* Modify this delete method:
        An instructor cannot be deleted if it's referenced by a course (Integrity Constraint).
        You must remove the instructor reference from the course first (set to null).
     */
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // *** Modify This Method (for courses class)

        // get the courses
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        // delete the Instructor
        // this will also delete the InstructorDetail object
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        // bidirectional mapping (reverse)
        return entityManager.find(InstructorDetail.class, theId);
    }

    // cascade delete (reverse)
    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // *** Remove the associated object reference
        // break bidirectional link first
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // then, delete the instructor detail (cascade delete to instructor as well)
        entityManager.remove(tempInstructorDetail);
    }

    // Lazy load courses for Instructor
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);  // here, without colon ":"

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    // JOIN FETCH
    @Override
    public Instructor findInstructorByJoinFetch(int theId) {

        // create query (JPQL - Java / JPA? Persistence Query Language)
        TypedQuery<Instructor> query = entityManager.createQuery(
//                "select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);
                "select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id = :data",
                    Instructor.class);      // to minimize N+1 query problem
        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        // find by id
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void updateCourse(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(tempCourse);
    }

    /* NEW: Course -> Review */

    @Override
    @Transactional
    public void saveCourse(Course theCourse) {
        // This will also save the reviews (due to "Cascade ALL")
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }
}
