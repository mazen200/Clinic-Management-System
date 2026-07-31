package com.netpoint.clinic.service;

import com.netpoint.clinic.DTO.DoctorDTO;
import com.netpoint.clinic.mappers.DoctorMapper;
import com.netpoint.clinic.model.Doctor;
import com.netpoint.clinic.repository.DoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    public List<DoctorDTO> findAllDocsActiveAndspecId(Boolean isActive,Long specId) {

        List<Doctor> doctors=new ArrayList<>();

        if(isActive!=null && specId!=null)
           doctors =doctorRepo.findallDocsActiveAndspecId(isActive,specId);
        else
           doctors =doctorRepo.findAll();

        return doctors.stream().map(doctorMapper::toDto).toList();
    }

    public DoctorDTO findDocByID(Long id) {
        Doctor doc=doctorRepo.findById(id).get();
        return doctorMapper.toDto(doc);
    }

    public DoctorDTO findDocByName(String name) {
        return doctorMapper.toDto(doctorRepo.findByNameLike(name).orElse(null));
    }
}
