package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.ClassTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<ClassTime, Long> {
    Optional<ClassTime> findByClassName(String name);

}
