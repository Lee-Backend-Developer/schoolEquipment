package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Classtimelist;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<Classtimelist, Long> {
    Optional<Classtimelist> findByClassName(String name);
    List<Classtimelist> findByDayOfWeekEquals(DayOfWeekEnum dayOfWeek);
    Optional<Classtimelist> findByClassNameEqualsAndDayOfWeek(String className, DayOfWeekEnum dayOfWeek);
}
