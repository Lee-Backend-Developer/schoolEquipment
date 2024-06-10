package com.equipment.school_equipment.request.notificationProduct;

import lombok.Builder;

@Builder
public record NotificationCreate(
        String subject,
        String content,
        String img
) {

}
