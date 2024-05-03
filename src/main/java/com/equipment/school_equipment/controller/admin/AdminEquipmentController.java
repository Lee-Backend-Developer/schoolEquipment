package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.response.thymeleaf.EquipmentResponse;
import com.equipment.school_equipment.service.EquipmentService;
import com.equipment.school_equipment.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/equipment")
public class AdminEquipmentController {
    private final EquipmentService equipmentService;
    private final RentalService rentalService;

    @GetMapping
    public String adminEquipments(Model model) {
        List<Equipment> equipments = equipmentService.findAll();
        List<EquipmentResponse> equipmentResponseList = equipments.stream().map(equipment -> EquipmentResponse
                .builder()
                .id(equipment.getId())
                        .equipmentName(equipment.getName())
                        .leftCnt(equipment.getCount())
                        .retCnt(rentalService.findByEquipmentCnt(equipment.getName()))
                        .build())
                .toList();

        model.addAttribute("equipments", equipmentResponseList);
        return "/admin/equipment/equipmentFindAll";
    }

}
