package com.equipment.school_equipment.request.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import static com.equipment.school_equipment.message.error.AdminCategoryMessage.EDIT_CATEGORY_NAME_ERROR;

@Builder
public record CategoryEditResponse(
        Long categoryId,
        Long id,
        String oldClassname,

        @NotBlank(message = EDIT_CATEGORY_NAME_ERROR)
        String changeNameClassname
) {

}
