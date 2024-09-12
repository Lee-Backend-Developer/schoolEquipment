package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.repository.dto.TodayRentalSelectDto;
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
    /**
     * 대여한 장비와 오늘 요일를 같이 조회하여 장비에 대한 데이터를 전달함
     * @param pageable 페이징
     * @return TodayRentalSelectDto
     */
    Page<TodayRentalSelectDto> findRentalJoinTodayRental(Pageable pageable);

    /**
     * 대여한 장비와 오늘 요일를 같이 조회하되, 카테고리에 맞게 조회
     * @param category 카테고리
     * @param pageable 페이징
     * @return TodayRentalSelectDto
     */
    Page<TodayRentalSelectDto> findCategoryAndEquipment(String category, Pageable pageable);
}
