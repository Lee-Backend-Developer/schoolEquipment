package com.equipment.school_equipment.response.thymeleaf.admin;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id, String name
) {
}
