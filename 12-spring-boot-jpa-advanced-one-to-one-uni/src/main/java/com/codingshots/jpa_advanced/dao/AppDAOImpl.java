package com.codingshots.jpa_advanced.dao;

import com.codingshots.jpa_advanced.entity.Instructor;
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
}
