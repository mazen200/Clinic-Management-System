package com.netpoint.clinic.mappers;

import com.netpoint.clinic.DTO.SpecializationResponseDTO;
import com.netpoint.clinic.model.Specializtions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {

    SpecializationResponseDTO toDto(Specializtions specialization);

    Specializtions toEntity(SpecializationResponseDTO dto);
}
