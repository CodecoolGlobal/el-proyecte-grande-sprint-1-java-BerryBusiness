package com.codecool.imf.service;

import com.codecool.imf.controller.dto.AppointmentDTO;
import com.codecool.imf.dao.AppointmentDAO;
import com.codecool.imf.dao.AppointmentDAOImpl;
import com.codecool.imf.dao.model.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentDAO mockAppointmentDAO;
    private AppointmentService appointmentService;
    private final List<Appointment> bookedAppointments = new ArrayList<>();
    private final List<LocalDateTime> bookedAppointmentsLocalDates = new ArrayList<>();

    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService(mockAppointmentDAO);

        bookedAppointmentsLocalDates.add(LocalDateTime.of(2023, Month.OCTOBER, 6, 9, 0));
        bookedAppointmentsLocalDates.add(LocalDateTime.of(2023, Month.OCTOBER, 6, 10, 30));
        bookedAppointmentsLocalDates.add(LocalDateTime.of(2023, Month.OCTOBER, 6, 11, 30));
        bookedAppointmentsLocalDates.add(LocalDateTime.of(2023, Month.OCTOBER, 6, 15, 30));
        bookedAppointmentsLocalDates.add(LocalDateTime.of(2023, Month.OCTOBER, 6, 16, 0));

        for (LocalDateTime bookedAppointmentsLocalDate : bookedAppointmentsLocalDates) {
            bookedAppointments.add(new Appointment(bookedAppointmentsLocalDate));
        }

        System.out.println(bookedAppointmentsLocalDates);
        System.out.println(bookedAppointments);
    }

    @Test
    void getAllAppointmentsForDay() {
        // ARRANGE
        String test = "test";
        when(mockAppointmentDAO.getAllForDay(test)).thenReturn(bookedAppointments);
        // ACT
        List<AppointmentDTO> result = appointmentService.getAllAppointmentsForDay(test);
        int expected = bookedAppointments.size();
        // ASSERT
        assertNotNull(result);
        assertEquals(expected, result.size());
    }

    @Test
    void addNewAppointment() {
    }

    @Test
    void deleteAppointment() {
    }
}