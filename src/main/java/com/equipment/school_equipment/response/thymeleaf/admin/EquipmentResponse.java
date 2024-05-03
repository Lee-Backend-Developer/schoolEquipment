package com.equipment.school_equipment.response.thymeleaf.admin;

public record EquipmentResponse(
        String equipmentName,
        String content,
        String img,
        int retCnt,
        int leftCnt
) {
}
