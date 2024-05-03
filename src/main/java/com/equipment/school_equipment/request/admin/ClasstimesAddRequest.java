package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import lombok.Builder;

@Builder
public record ClasstimesAddRequest(
        String classname,
        DayOfWeekEnum[] dayOfWeekEnum,
        boolean oneTime,
        boolean twoTime,
        boolean threeTime,
        boolean fourTime,
        boolean fiveTime,
        boolean sixTime,
        boolean sevenTime,
        boolean eightTime,
        boolean nineTime,
        boolean tenTime
) {
}
