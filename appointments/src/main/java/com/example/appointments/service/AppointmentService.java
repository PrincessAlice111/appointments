package com.example.appointments.service;

import java.util.List;
import java.util.Optional;
import com.example.appointments.model.Appointment;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(Long id);

    Appointment createAppointment(Appointment appointment);

    Appointment updateAppointment(Long id, Appointment appointment);

    void deleteAppointment(Long id);
}
