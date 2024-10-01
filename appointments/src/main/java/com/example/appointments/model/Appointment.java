package com.example.appointments.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment extends RepresentationModel<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Time is mandatory.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "Time must be in the format \"yyyy-mm-dd hh:mm\".")
    @Column(name = "time")
    private String time;

    @NotBlank(message = "Doctor is mandatory.")
    @Column(name = "doctor")
    private String doctor;

    @NotBlank(message = "Speciality is mandatory.")
    @Column(name = "speciality")
    private String speciality;

    @NotBlank(message = "Status is mandatory.")
    @Pattern(regexp = "^(available|taken)$", message = "Status must be either \"available\" or \"taken\".")
    @Column(name = "status")
    private String status;

    @Column(name = "patient")
    private String patient;

    // Getters.
    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getStatus() {
        return status;
    }

    public String getPatient() {
        return patient;
    }

    // Setters.
    public void setId(Long id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
