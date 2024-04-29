package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.ClassTimeList;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassTimeRepository extends JpaRepository<ClassTimeList, Long> {
    Optional<ClassTimeList> findByClassName(String name);
    List<ClassTimeList> findByDayOfWeekEquals(DayOfWeekEnum dayOfWeek);
    Optional<ClassTimeList> findByClassNameEqualsAndDayOfWeek(String className, DayOfWeekEnum dayOfWeek);
}
