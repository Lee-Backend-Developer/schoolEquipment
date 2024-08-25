package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.dto.TodayRentalSelect;
import com.equipment.school_equipment.request.admin.RentalPageCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentalRepositoryCustom {
    Optional<Rental> findByClassOfDay(String className, String classOfDay);
    List<Rental> findRentals(String equipmentName);
    List<Equipment> findByClassnameIdAndDayOfWeek(String classNameId, String dayOfWeek);
    Optional<Rental> findByClassIdAndEquipmentId(Long classId, Long equipmentId);
    Page<Rental> findAllAndRentalChkTruePage(RentalPageCondition condition, Pageable pageable);
    Page<Rental> findAllAndRentalCategoryPage(RentalPageCondition condition, Pageable pageable);
    List<Rental> findByWeekday(DayOfWeekEnum weekday);
    // 대여 Join 오늘날 대여
    List<TodayRentalSelect> findRentalJoinTodayRental();
}
