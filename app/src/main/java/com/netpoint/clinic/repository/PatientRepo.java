package com.netpoint.clinic.repository;

import com.netpoint.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {

    @Query("select p.name from Patient p where p.age> :age order by p.name asc")
    List<String> findPatientNamesByageGreaterThan(@Param("age")int age);

    @Query("select count(p) from Patient p where p.isActive=true ")
    int findActivePatientsCount();


}
