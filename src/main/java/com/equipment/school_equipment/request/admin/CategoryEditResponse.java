package com.equipment.school_equipment.request.admin;

import lombok.Builder;

@Builder
public record CategoryEditResponse(
        Long id,
        String oldClassname,
        String changeNameClassname
) {
}
