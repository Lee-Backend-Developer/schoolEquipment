package com.equipment.school_equipment.request.rental;

import lombok.Builder;

@Builder
public record RentalCreate(
        String className, String equipmentName, int equipmentCount
) {}
