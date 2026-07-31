package com.netpoint.clinic.mappers;

import com.netpoint.clinic.DTO.PatientDTO;
import com.netpoint.clinic.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);
}
