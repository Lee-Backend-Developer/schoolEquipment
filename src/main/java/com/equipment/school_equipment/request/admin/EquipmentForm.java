package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.message.error.AdminErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Data
public class EquipmentForm {
    // 추가할 때 및 공용
    @NotBlank(message = AdminErrorMessage.EQUIPMENT_ADD_ERROR)
    private String name;

    private String content;

    private MultipartFile image;

    @Min(value = 1, message = AdminErrorMessage.EQUIPMENT_ADD_COUNT_ERROR)
    private int count;

    @NotNull(message = AdminErrorMessage.EQUIPMENT_ADD_CATEGORY_ERROR)
    private SecondaryCategory secondaryCategory;

    @NotNull(message = AdminErrorMessage.EQUIPMENT_ADD_CATEGORY_ERROR)
    private PrimaryCategory primaryCategory;

    private List<SecondaryCategory> secondaryCategoryList;
    private List<PrimaryCategory> primaryCategories;

    private String categoryName;

    // 수정할 때 사용
    private String imageName;
    private Long categoryId;
    private Long equipmentId;
}