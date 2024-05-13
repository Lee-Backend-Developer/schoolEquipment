package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalRepositoryCustom {
    Optional<Rental> findByClassOfDay(String className, String classOfDay);
    List<Rental> findRentals(String equipmentName);
    List<Equipment> findByClassnameIdAndDayOfWeek(String classNameId, String dayOfWeek);
    Optional<Rental> findByClassIdAndEquipmentId(Long classId, Long equipmentId);
    List<Rental> findAllAndRentalChkTrue();
}
