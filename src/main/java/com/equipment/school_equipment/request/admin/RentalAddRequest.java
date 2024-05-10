package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record RentalAddRequest(
        Long equipmentId,
        Long classroomId,
        int retCnt,
        List<Category> categories,
        List<Equipment> equipments,
        List<Classtimes> classtimes,
        DayOfWeekEnum[] weekday
) {
}
