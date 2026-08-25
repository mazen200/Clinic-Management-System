package com.netpoint.clinic.dtos;

import com.netpoint.clinic.enums.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleDTO {

    private Long id;
    private DoctorDTO doctor;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
