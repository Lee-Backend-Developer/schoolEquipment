package com.equipment.school_equipment.request.admin;

import lombok.Builder;

@Builder
public record EquipmentEditRequest(
        Long id,
        String name,
        Long categoryId,
        String mainImg,
        String content,
        int count
) {
}
