package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.ScheduleDTO;
import com.netpoint.clinic.model.DoctorSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleDTO toDto(DoctorSchedule schedule);

    DoctorSchedule toEntity(ScheduleDTO scheduleDTO);
}
