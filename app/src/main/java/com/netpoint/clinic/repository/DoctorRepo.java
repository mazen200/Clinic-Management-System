package com.netpoint.clinic.repository;

import com.netpoint.clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    // get all doctors (iasActive=true,SpecializationsId)

    @Query("select d from Doctor d where d.isActive=:isactive And d.specializtion.id=:id")
   List<Doctor> findallDocsActiveAndspecId( @Param("isactive") Boolean isactive,@Param("id") Long specId);


    Optional<Doctor> findByNameLike(String name);
}
