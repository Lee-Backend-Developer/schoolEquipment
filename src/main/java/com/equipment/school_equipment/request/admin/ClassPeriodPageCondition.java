package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
public class ClassPeriodPageCondition {

    private final Integer page;
    private final DayOfWeekEnum week;

    @Builder
    public ClassPeriodPageCondition(Integer page, DayOfWeekEnum week) {
        this.page = (Objects.isNull(page) || (page <= -1) ? 0 : page);
        this.week = week;
    }
}
