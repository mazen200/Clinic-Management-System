package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.PatientDTO;
import com.netpoint.clinic.model.Patient;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-25T21:29:42+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setName( patient.getName() );
        patientDTO.setPhone( patient.getPhone() );
        patientDTO.setAddress( patient.getAddress() );
        patientDTO.setAge( patient.getAge() );
        patientDTO.setNationality( patient.getNationality() );
        patientDTO.setBloodType( patient.getBloodType() );
        patientDTO.setCreatedAt( patient.getCreatedAt() );
        patientDTO.setActive( patient.isActive() );

        return patientDTO;
    }
}
