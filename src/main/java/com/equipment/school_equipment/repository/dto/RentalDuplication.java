package com.equipment.school_equipment.repository.dto;

import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.Equipment;

public record RentalDuplication(
        Classtimes classtimes,
        Equipment equipment,
        int sumCnt
) {
}
