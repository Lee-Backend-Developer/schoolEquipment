package com.equipment.school_equipment.response.thymeleaf;

import lombok.Builder;

@Builder
public record RentalEquipmentResponse(
        String name,
        String content,
        String image,
        String category,
        int retCnt, //원래냥
        int leftCnt //남은량
) {
}
