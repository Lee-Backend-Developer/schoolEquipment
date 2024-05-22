package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.ClassPeriod;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<ClassPeriod, Long> {
    Optional<ClassPeriod> findByClassName(String name);
    List<ClassPeriod> findByDayOfWeekEquals(DayOfWeekEnum dayOfWeek);
    Optional<ClassPeriod> findByClassNameEqualsAndDayOfWeek(String className, DayOfWeekEnum dayOfWeek);
}
