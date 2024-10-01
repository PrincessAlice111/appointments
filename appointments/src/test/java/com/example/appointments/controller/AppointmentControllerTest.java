package com.example.appointments.controller;

import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.appointments.model.Appointment;
import com.example.appointments.service.AppointmentService;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    void getAllAppointmentsSuccess() throws Exception {
        // Arrange.
        List<Appointment> appointments = List.of(
                new Appointment(1L, "2024-09-16 12:00", "Doctor One", "cardiology", "taken", "Patient One"),
                new Appointment(2L, "2024-09-16 13:00", "Doctor Two", "dermatology", "taken", "Patient Two"),
                new Appointment(3L, "2024-09-16 14:00", "Doctor Three", "neurology", "taken", "Patient Three"));
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        // Act and assert.
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.appointmentList", Matchers.hasSize(3)))
                .andExpect(jsonPath("$._embedded.appointmentList[0].time", Matchers.is("2024-09-16 12:00")))
                .andExpect(jsonPath("$._embedded.appointmentList[1].time", Matchers.is("2024-09-16 13:00")))
                .andExpect(jsonPath("$._embedded.appointmentList[2].time", Matchers.is("2024-09-16 14:00")))
                .andExpect(jsonPath("$._embedded.appointmentList[0].doctor", Matchers.is("Doctor One")))
                .andExpect(jsonPath("$._embedded.appointmentList[1].doctor", Matchers.is("Doctor Two")))
                .andExpect(jsonPath("$._embedded.appointmentList[2].doctor", Matchers.is("Doctor Three")))
                .andExpect(jsonPath("$._embedded.appointmentList[0].speciality", Matchers.is("cardiology")))
                .andExpect(jsonPath("$._embedded.appointmentList[1].speciality", Matchers.is("dermatology")))
                .andExpect(jsonPath("$._embedded.appointmentList[2].speciality", Matchers.is("neurology")))
                .andExpect(jsonPath("$._embedded.appointmentList[0].status", Matchers.is("taken")))
                .andExpect(jsonPath("$._embedded.appointmentList[1].status", Matchers.is("taken")))
                .andExpect(jsonPath("$._embedded.appointmentList[2].status", Matchers.is("taken")))
                .andExpect(jsonPath("$._embedded.appointmentList[0].patient", Matchers.is("Patient One")))
                .andExpect(jsonPath("$._embedded.appointmentList[1].patient", Matchers.is("Patient Two")))
                .andExpect(jsonPath("$._embedded.appointmentList[2].patient", Matchers.is("Patient Three")))
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    void getAllAppointmentsEmptyList() throws Exception {
        // Arrange.
        List<Appointment> appointments = List.of();
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        // Act and assert.
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    void getAppointmentByIdSuccess() throws Exception {
        // Arrange.
        Optional<Appointment> appointment = Optional.of(
                new Appointment(1L, "2024-09-16 12:00", "Doctor One", "cardiology", "taken", "Patient One"));
        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        // Act and assert.
        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.time", Matchers.is("2024-09-16 12:00")))
                .andExpect(jsonPath("$.doctor", Matchers.is("Doctor One")))
                .andExpect(jsonPath("$.speciality", Matchers.is("cardiology")))
                .andExpect(jsonPath("$.status", Matchers.is("taken")))
                .andExpect(jsonPath("$.patient", Matchers.is("Patient One")))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.collection").exists());
    }

    @Test
    void getAppointmentByIdNotFound() throws Exception {
        // Arrange.
        Optional<Appointment> appointment = Optional.empty();
        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        // Act and assert.
        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Appointment 1 does not exist."));
    }
}
