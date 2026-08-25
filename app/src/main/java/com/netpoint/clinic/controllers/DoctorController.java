package com.netpoint.clinic.controllers;

import com.netpoint.clinic.dtos.DoctorDTO;
import com.netpoint.clinic.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getDoctors(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Long specId) {
        return ResponseEntity.ok(doctorService.findAllDocsActiveAndspecId(active, specId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDocById(@PathVariable Long id) {
        DoctorDTO doctor = doctorService.findDocByID(id);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }
}

