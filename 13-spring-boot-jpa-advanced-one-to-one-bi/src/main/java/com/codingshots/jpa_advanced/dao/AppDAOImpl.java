package com.codingshots.jpa_advanced.dao;

import com.codingshots.jpa_advanced.entity.Instructor;
import com.codingshots.jpa_advanced.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

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

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

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
}
