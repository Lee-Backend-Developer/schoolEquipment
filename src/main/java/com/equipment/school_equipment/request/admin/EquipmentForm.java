package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.message.error.AdminErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class EquipmentForm {
    // 추가할 때 및 공용
    @NotBlank(message = AdminErrorMessage.EQUIPMENT_ADD_ERROR)
    private String name;

    @NotBlank(message = AdminErrorMessage.EQUIPMENT_ADD_CONTENT_ERROR)
    private String content;

    private MultipartFile image;

    @Min(value = 1, message = AdminErrorMessage.EQUIPMENT_ADD_COUNT_ERROR)
    private int count;

    @NotNull(message = AdminErrorMessage.EQUIPMENT_ADD_CATEGORY_ERROR)
    private Category category;

    private List<Category> categories = new ArrayList<>();
    private String categoryName;

    // 수정할 때 사용
    private String imageName;
    private Long categoryId;
    private Long equipmentId;
}