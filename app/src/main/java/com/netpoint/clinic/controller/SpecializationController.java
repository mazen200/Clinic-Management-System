package com.netpoint.clinic.controller;

import com.netpoint.clinic.DTO.CreateSpecializationDTO;
import com.netpoint.clinic.DTO.SpecializationResponseDTO;
import com.netpoint.clinic.service.SpecializationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/spec")
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping
    public ResponseEntity<SpecializationResponseDTO> addSpecialization(@RequestBody CreateSpecializationDTO createSpecializationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specializationService.CreateSpecialization(createSpecializationDTO));
    }

    @GetMapping
    public ResponseEntity<List<SpecializationResponseDTO>> getSpecializations() {
        List<SpecializationResponseDTO> responses = specializationService.findallSpecs();
        return ResponseEntity.ok(responses);
    }
}

