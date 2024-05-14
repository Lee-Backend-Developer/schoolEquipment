package com.equipment.school_equipment.response.thymeleaf;

import com.equipment.school_equipment.message.error.AdminErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import static com.equipment.school_equipment.message.error.AdminErrorMessage.*;

@Builder
public record EquipmentRequest(
        Long id,
        @NotBlank(message = EQUIPMENT_ADD_ERROR)
        String equipmentName,
        String content,
        String img,
        @Min(value = 1, message = EQUIPMENT_ADD_COUNT_ERROR)
        int retCnt,
        int leftCnt
) {
}
