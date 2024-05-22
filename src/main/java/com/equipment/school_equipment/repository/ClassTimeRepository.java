package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Classes;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<Classes, Long> {
    Optional<Classes> findByClassName(String name);
    List<Classes> findByDayOfWeekEquals(DayOfWeekEnum dayOfWeek);
    Optional<Classes> findByClassNameEqualsAndDayOfWeek(String className, DayOfWeekEnum dayOfWeek);
}
