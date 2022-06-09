package com.example.doan_thibanglaixe.data.model;

public class Exam {
    Integer id;
    String name;

    public Exam() {
    }

    public Exam(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
