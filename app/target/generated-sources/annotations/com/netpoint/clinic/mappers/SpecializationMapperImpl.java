package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.SpecializationResponseDTO;
import com.netpoint.clinic.model.Specializtions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-25T21:29:43+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class SpecializationMapperImpl implements SpecializationMapper {

    @Override
    public SpecializationResponseDTO toDto(Specializtions specialization) {
        if ( specialization == null ) {
            return null;
        }

        SpecializationResponseDTO specializationResponseDTO = new SpecializationResponseDTO();

        specializationResponseDTO.setId( specialization.getId() );
        specializationResponseDTO.setName( specialization.getName() );
        specializationResponseDTO.setDescription( specialization.getDescription() );

        return specializationResponseDTO;
    }

    @Override
    public Specializtions toEntity(SpecializationResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Specializtions specializtions = new Specializtions();

        specializtions.setId( dto.getId() );
        specializtions.setName( dto.getName() );
        specializtions.setDescription( dto.getDescription() );

        return specializtions;
    }
}
