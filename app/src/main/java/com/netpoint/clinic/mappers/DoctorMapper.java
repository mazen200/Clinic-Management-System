package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.DoctorDTO;
import com.netpoint.clinic.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDTO toDto(Doctor doctor);
}
