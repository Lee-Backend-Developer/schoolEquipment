package com.equipment.school_equipment.request.notificationProduct;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record NotificationRequest(
        Long id,
        MultipartFile imageFile,
        String subject,
        String content,
        // 유의사항 수정
        String notificationContent
) {

}
