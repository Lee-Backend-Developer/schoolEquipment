package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.Category;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
public record EquipmentForm(
        // 추가할 때 및 공용
        String name,
        String content,
        MultipartFile image,
        int count,
        List<Category> categories,
        // 수정할 때 사용
        String imageName,
        String categoryName
) {

}
