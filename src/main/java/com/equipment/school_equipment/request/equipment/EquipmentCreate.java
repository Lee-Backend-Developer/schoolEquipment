package com.equipment.school_equipment.request.equipment;

import com.equipment.school_equipment.domain.Category;
import lombok.Builder;

@Builder
public record EquipmentCreate(
    String name,
    int count,
    String image,
    Long categoryId
) {
}
