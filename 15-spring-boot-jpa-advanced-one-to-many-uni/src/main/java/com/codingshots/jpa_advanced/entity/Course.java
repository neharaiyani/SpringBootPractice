package com.codingshots.jpa_advanced.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    // define our fields

    // define constructors

    // define getters / setters methods

    // define toString() method

    // *** annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    /*** Many-to-One Mapping, Bidirectional, No Cascade Delete ***/
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")     // "instructor_id" is a column in Instructor table in DB
    private Instructor instructor;

    /*** NEW: One-to-Many Mapping, Uni-directional, with Cascade Delete, Lazy loading ***/
    // Here, we mapped Foreign Key column slightly differently because it's a Uni-directional Mapping from Course to Review
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public Course(){}

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /*** NEW: add convenient method (for adding multiple reviews), necessary for "Many" mapping ***/
    public void addReview(Review theReview){
        if(reviews == null){
            reviews = new ArrayList<>();
        }

        // Course -> Review (uni-directional), so no 2 way association required
        reviews.add(theReview);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
