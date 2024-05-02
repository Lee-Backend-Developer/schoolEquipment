package com.equipment.school_equipment.response.thymeleaf;

import lombok.Builder;

@Builder
public record EquipmentResponse(
        String equipmentName, String content, String img, int retCnt, int leftCnt

) {
}
