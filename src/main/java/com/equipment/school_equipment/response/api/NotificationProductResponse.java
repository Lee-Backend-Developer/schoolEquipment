package com.equipment.school_equipment.response.api;

import lombok.Builder;

@Builder
public record NotificationProductResponse(
        Long id,
        String subject,
        String content,
        String img
) {
}
