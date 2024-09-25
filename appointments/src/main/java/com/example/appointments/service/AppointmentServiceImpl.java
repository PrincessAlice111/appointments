package com.example.appointments.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appointments.model.Appointment;
import com.example.appointments.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment createAppointment(Appointment student) {
        return appointmentRepository.save(student);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment student) {
        if (appointmentRepository.existsById(id)) {
            student.setId(id);
            return appointmentRepository.save(student);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
