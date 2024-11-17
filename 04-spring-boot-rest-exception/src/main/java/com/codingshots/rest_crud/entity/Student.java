package com.codingshots.rest_crud.entity;

public class Student {

    private String firstNAme;
    private String lastNAme;

    public Student() {
    }

    public Student(String firstNAme, String lastNAme) {
        this.firstNAme = firstNAme;
        this.lastNAme = lastNAme;
    }

    public String getFirstNAme() {
        return firstNAme;
    }

    public void setFirstNAme(String firstNAme) {
        this.firstNAme = firstNAme;
    }

    public String getLastNAme() {
        return lastNAme;
    }

    public void setLastNAme(String lastNAme) {
        this.lastNAme = lastNAme;
    }
}
