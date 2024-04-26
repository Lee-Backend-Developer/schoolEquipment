package com.equipment.school_equipment.response.thymeleaf;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
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
