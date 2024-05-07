package com.equipment.school_equipment.request.admin;

import lombok.Builder;

@Builder
public record EquipmentAddRequest(
        String name,
        int count
) {
}
