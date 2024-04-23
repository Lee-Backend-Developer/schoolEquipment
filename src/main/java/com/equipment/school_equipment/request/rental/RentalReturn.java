package com.equipment.school_equipment.request.rental;

import lombok.Builder;

@Builder
public record RentalReturn(
        String classname, String equipmentName, int retCount
) {
}
