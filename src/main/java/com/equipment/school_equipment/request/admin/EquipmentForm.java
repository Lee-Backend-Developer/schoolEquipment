package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.Category;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
public record EquipmentForm(
        String name,
        String content,
        MultipartFile image,
        int count,
        List<Category> categories
) {

}
