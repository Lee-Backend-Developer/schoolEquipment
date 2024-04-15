package com.equipment.school_equipment.request.rental;

import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.domain.Equipment;
import lombok.Builder;

@Builder
public record RentalCreate(
        ClassTime classTime, Equipment equipment
) {}
