package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<Classtimes, Long> {
    Optional<Classtimes> findByClassName(String name);
    List<Classtimes> findByDayOfWeekEquals(DayOfWeekEnum dayOfWeek);
    Optional<Classtimes> findByClassNameEqualsAndDayOfWeek(String className, DayOfWeekEnum dayOfWeek);
}
