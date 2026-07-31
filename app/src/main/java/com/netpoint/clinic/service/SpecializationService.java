package com.netpoint.clinic.service;

import com.netpoint.clinic.DTO.CreateSpecializationDTO;
import com.netpoint.clinic.DTO.SpecializationResponseDTO;
import com.netpoint.clinic.mappers.SpecializationMapper;
import com.netpoint.clinic.model.Specializtions;
import com.netpoint.clinic.repository.SpecializationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SpecializationService {

    private final SpecializationRepo specRepo;
    private final SpecializationMapper specializationMapper;

    public SpecializationResponseDTO CreateSpecialization(CreateSpecializationDTO createSpecializationDTO) {
        Specializtions spec = new Specializtions();
        spec.setName(createSpecializationDTO.getName());
        spec.setDescription(createSpecializationDTO.getDescription());

        return specializationMapper.toDto(specRepo.save(spec));
    }

    public List<SpecializationResponseDTO> findallSpecs() {
        return specRepo.findAll()
                .stream()
                .map(specializationMapper::toDto)
                .toList();
    }
}

