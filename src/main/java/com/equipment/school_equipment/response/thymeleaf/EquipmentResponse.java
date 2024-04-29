package com.equipment.school_equipment.response.thymeleaf;

import lombok.Builder;

@Builder
public record EquipmentResponse(
        String equipmentName, int retCnt, int leftCnt

) {
}
