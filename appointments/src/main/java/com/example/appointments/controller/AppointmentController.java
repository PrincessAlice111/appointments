package com.example.appointments.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.appointments.model.Appointment;
import com.example.appointments.model.Doctor;
import com.example.appointments.model.Speciality;
import com.example.appointments.model.Status;

@RestController
public class AppointmentController {
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentController() {
        // Create dummy doctors.
        Doctor doctor1 = new Doctor(1, "Doctor", "One");
        Doctor doctor2 = new Doctor(2, "Doctor", "Two");
        Doctor doctor3 = new Doctor(3, "Doctor", "Three");
        // Create some specialities.
        Speciality speciality1 = new Speciality(1, "cardiology");
        Speciality speciality2 = new Speciality(2, "dermatology");
        Speciality speciality3 = new Speciality(3, "neurology");
        // Create statuses.
        Status status1 = new Status(1, "available");
        Status status2 = new Status(2, "taken");
        // Initialise the list of appointments with some data.
        appointments.add(new Appointment(1, "2024-08-26 12:00", doctor1, speciality1, status1));
        appointments.add(new Appointment(2, "2024-08-26 13:00", doctor1, speciality1, status1));
        appointments.add(new Appointment(3, "2024-08-26 14:00", doctor1, speciality1, status1));
        appointments.add(new Appointment(4, "2024-08-26 12:00", doctor2, speciality2, status1));
        appointments.add(new Appointment(5, "2024-08-26 13:00", doctor2, speciality2, status1));
        appointments.add(new Appointment(6, "2024-08-26 14:00", doctor2, speciality2, status1));
        appointments.add(new Appointment(7, "2024-08-26 12:00", doctor3, speciality3, status2));
        appointments.add(new Appointment(8, "2024-08-26 13:00", doctor3, speciality3, status2));
        appointments.add(new Appointment(9, "2024-08-26 14:00", doctor3, speciality3, status2));
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return appointments;
    }

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null;
    }
}
