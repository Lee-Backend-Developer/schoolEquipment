package com.equipment.school_equipment.response.thymeleaf.admin;

import lombok.Builder;

@Builder
public record RentalFindAllResponse(
        Long id,
        String week,
        String className,
        String equipmentName,
        boolean rentalChk,
        int rentalCnt
) {
}
