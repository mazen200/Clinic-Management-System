package com.netpoint.clinic.repository;

import com.netpoint.clinic.model.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<DoctorSchedule, Long> {

    Optional<List<DoctorSchedule>> findByDoctorId(Long doctorId);
}

