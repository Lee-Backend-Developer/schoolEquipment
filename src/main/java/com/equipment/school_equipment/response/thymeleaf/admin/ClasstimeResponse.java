package com.equipment.school_equipment.response.thymeleaf.admin;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import lombok.Builder;

@Builder
public record ClasstimeResponse(
        Long id,
        String classname,
        DayOfWeekEnum dayOfWeekEnum,
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
