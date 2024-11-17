package com.codingshots.jpa_advanced.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    // define fields
    // define constructors
    // define getters / setters
    // define toString()
    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    // this field is not required, as its mapping is done in "Course" class in a slightly different way
//    private int course_id;

    public Review(){}

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
