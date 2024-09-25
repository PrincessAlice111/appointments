package com.example.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.appointments.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
