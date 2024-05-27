package com.equipment.school_equipment.request.equipment;

import lombok.Builder;

@Builder
public record EquipmentCreate(
    String name,
    String equimentContent,
    int count,
    String image,
    Long categoryId
) {
}
