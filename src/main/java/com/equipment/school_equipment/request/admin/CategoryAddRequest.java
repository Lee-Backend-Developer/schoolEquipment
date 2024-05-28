package com.equipment.school_equipment.request.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.equipment.school_equipment.message.error.AdminErrorMessage.CATEGORY_ADD_ERROR;

public record CategoryAddRequest(
        @NotBlank(message = CATEGORY_ADD_ERROR)
        @NotNull(message = CATEGORY_ADD_ERROR)
        String name
) {
}
