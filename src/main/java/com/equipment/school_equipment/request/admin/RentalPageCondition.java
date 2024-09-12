package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import lombok.Data;

@Data
public class RentalPageCondition {
    private int page;
    private DayOfWeekEnum week;
    private String category;
}
