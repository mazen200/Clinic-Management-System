package com.netpoint.clinic.repository;

import com.netpoint.clinic.model.Specializtions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepo  extends JpaRepository<Specializtions, Long> {
}
