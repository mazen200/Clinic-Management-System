package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.AppointmentDto;
import com.netpoint.clinic.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper  {

    @Mapping(source= "doctor.id", target= "doctorId")
    @Mapping(source= "patient.id", target= "patientId")
    AppointmentDto toDto(Appointment appointment);

}
