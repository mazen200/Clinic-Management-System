package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.DoctorDTO;
import com.netpoint.clinic.model.Doctor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-25T21:29:43+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO toDto(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setName( doctor.getName() );
        doctorDTO.setConsultationFee( doctor.getConsultationFee() );
        doctorDTO.setActive( doctor.isActive() );
        doctorDTO.setCreatedAt( doctor.getCreatedAt() );

        return doctorDTO;
    }
}
