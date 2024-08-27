package com.example.appointments;

public class Doctor {
    private int id;
    private String name;
    private String surname;

    public Doctor(
            int id,
            String name,
            String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
