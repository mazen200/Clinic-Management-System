package com.netpoint.clinic.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequest {

    private LocalDate appointmentDate;

    private LocalDateTime appointmentTime;

    private Long doctorId;

    private int patientId;

}
