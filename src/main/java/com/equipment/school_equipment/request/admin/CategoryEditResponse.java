package com.equipment.school_equipment.request.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import static com.equipment.school_equipment.message.error.AdminErrorMessage.CATEGORY_EDIT_NAME_ERROR;

@Builder
public record CategoryEditResponse(
        Long categoryId,
        Long id,
        String oldClassname,

        @NotBlank(message = CATEGORY_EDIT_NAME_ERROR)
        String changeNameClassname
) {

}
