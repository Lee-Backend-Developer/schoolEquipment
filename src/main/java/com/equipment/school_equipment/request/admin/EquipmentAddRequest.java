package com.equipment.school_equipment.request.admin;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record EquipmentAddRequest(
        String name,
        MultipartFile image,
        int count
) {
}
