package com.equipment.school_equipment.request.rental;

import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import lombok.Builder;

@Builder
public record RentalCreate(
        String className, DayOfWeekEnum dayOfWeekEnum, String equipmentName, int equipmentCount
) {}
