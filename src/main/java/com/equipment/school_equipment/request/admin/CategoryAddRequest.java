package com.equipment.school_equipment.request.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.equipment.school_equipment.message.error.AdminCategoryMessage.ADD_CATEGORY_ERROR;

public record CategoryAddRequest(
        @NotBlank(message = ADD_CATEGORY_ERROR) @NotNull(message = ADD_CATEGORY_ERROR) String name
) {
}
