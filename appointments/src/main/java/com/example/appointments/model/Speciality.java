package com.example.appointments.model;

public class Speciality {
    private int id;
    private String name;

    public Speciality(
            int id,
            String name) {
        this.id = id;
        this.name = name;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
