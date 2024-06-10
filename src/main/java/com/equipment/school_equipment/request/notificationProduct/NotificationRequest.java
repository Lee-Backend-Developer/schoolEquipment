package com.equipment.school_equipment.request.notificationProduct;

import lombok.Builder;

@Builder
public record NotificationRequest(
        Long id,
        String subject,
        String content,
        String img
) {

}
