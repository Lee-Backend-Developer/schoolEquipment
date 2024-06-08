package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.response.api.EquipmentApiFindResponse;
import com.equipment.school_equipment.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/equipment/api")
public class AdminEquipmentApiController {

    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentApiFindResponse>> getFindByEquipment(Long primaryCategoryId, Long secondaryCategoryId){
        List<EquipmentApiFindResponse> equipmentApiFindResponses = equipmentService.
                findByEquipmentAndPrimaryCategoryAndSecondaryCategory(primaryCategoryId, secondaryCategoryId)
                .stream()
                .map(equipment -> EquipmentApiFindResponse.builder()
                        .id(equipment.getId())
                        .name(equipment.getName())
                        .count(equipment.getCount())
                        .build())
                .toList();
        return ResponseEntity.ok(equipmentApiFindResponses);
    }
}
