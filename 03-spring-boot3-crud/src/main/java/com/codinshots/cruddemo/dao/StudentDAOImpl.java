package com.codinshots.cruddemo.dao;

import com.codinshots.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired // optional here
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /******************** Save to Database ********************/
    // implement save method
    @Override
    @Transactional  // handle transaction automatically
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    /******************** Read from Database ********************/
    // no need of @Transactional since we are doing a query
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    /******************** Query from Database ********************/
    @Override
    public List<Student> findAll() {
        // create query: use JPA entity name and entity field
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY lastName", Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query: JPQL Named Parameters (here, theData) are prefixed with colon :
        // theData : a placeholder that is filled in later, no hard coding
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        // set query parameters
        theQuery.setParameter("theData", theLastName);

        // return query results
        return theQuery.getResultList();

        /*** "select" clause DEMO only ***/
        // here, 's' is an “identification variable” / alias, Provides a reference to the Student entity object
        // s - Can be any name, Useful for when you have complex queries
        // TypedQuery<Student> theQuery1 = entityManager.createQuery("select s FROM Student s", Student.class);
        // TypedQuery<Student> theQuery2 = entityManager.createQuery("select s FROM Student s WHERE s.email LIKE ‘%luv2code.com’", Student.class);
        // TypedQuery<Student> theQuery3 = entityManager.createQuery("select s FROM Student s WHERE s.lastName=:theData", Student.class);
    }

    /******************** Update to Database ********************/
    @Override
    @Transactional  // modifying database
    public void update(Student theStudent) {
        entityManager.merge(theStudent);

        // NOTE: update all rows, set lastName to "Tester"
        // int numRowsUpdated = entityManager.createQuery("UPDATE Student SET lastName=‘Tester’").executeUpdate();
    }

    /******************** Delete from Database ********************/
    @Override
    @Transactional // modifying database
    public void delete(Integer id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
