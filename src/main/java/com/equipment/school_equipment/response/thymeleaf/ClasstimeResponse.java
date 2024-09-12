package com.equipment.school_equipment.response.thymeleaf;

import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import lombok.Builder;

import java.util.List;


public record ClasstimeResponse(
        Long id,
        String classname,
        DayOfWeekEnum dayOfWeekEnum,
        List<Boolean> times
) {
    @Builder
    public ClasstimeResponse {
    }
}
