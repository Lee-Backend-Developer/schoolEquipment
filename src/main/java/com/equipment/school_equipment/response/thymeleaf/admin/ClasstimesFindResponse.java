package com.equipment.school_equipment.response.thymeleaf.admin;

import lombok.Builder;

@Builder
public record ClasstimesFindResponse(
        Long id,
        String name
) {
}
