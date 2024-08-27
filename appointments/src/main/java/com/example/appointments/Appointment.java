package com.example.appointments;

public class Appointment {
    private int id;
    private String date;
    private Doctor doctor;
    private Speciality speciality;
    private Status status;

    public Appointment(
            int id,
            String date,
            Doctor doctor,
            Speciality speciality,
            Status status) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.speciality = speciality;
        this.status = status;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public Status getStatus() {
        return status;
    }
}
