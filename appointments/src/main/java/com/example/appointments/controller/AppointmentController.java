package com.example.appointments.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appointments.model.Appointment;
import com.example.appointments.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Object> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Appointment " + id + " does not exist.");
        }
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<Object> createAppointment(@Validated @RequestBody Appointment newAppointment) {
        Appointment createdAppointment = appointmentService.createAppointment(newAppointment);
        if (createdAppointment == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Appointment could not be created.");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Appointment " + createdAppointment.getId() + " created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable Long id, @Validated @RequestBody Appointment newAppointment) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Appointment " + id + " does not exist.");
        }
        appointmentService.updateAppointment(id, newAppointment);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Appointment " + id + " updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Appointment " + id + " does not exist.");
        }
        appointmentService.deleteAppointment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Appointment " + id + " deleted successfully.");
    }
}
