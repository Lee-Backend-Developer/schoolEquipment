package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Classtimetable;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<Classtimetable, Long> {
    Optional<Classtimetable> findByClassName(String name);
    List<Classtimetable> findByDayOfWeek(DayOfWeekEnum week);
}
