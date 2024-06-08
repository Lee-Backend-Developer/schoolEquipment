package com.equipment.school_equipment.response.api;

import lombok.Builder;

@Builder
public record EquipmentApiFindResponse(
        Long id,
        String name,
        int count
) {
}
