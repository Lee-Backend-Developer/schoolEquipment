package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.response.thymeleaf.EquipmentRequest;
import com.equipment.school_equipment.service.EquipmentService;
import com.equipment.school_equipment.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final RentalService rentalService;
    final String PATH = "/equipment/";



    // 대여후 수량(가지고 있던 수량 - 렌탈마다 장비 수)
    // 응답: 장비이름, 대여 후 수량, 가지고 있던 주량
    @GetMapping
    public String equipment(Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        List<EquipmentRequest> responses = new ArrayList<>();
        Page<Equipment> equipmentPage = equipmentService.findAll(page, 15);
        for (Equipment equipment : equipmentPage.toList()) {
            String equipmentName = equipment.getName();
            EquipmentRequest response = EquipmentRequest.builder()
                    .equipmentName(equipmentName)
                    .content(equipment.getContent())
                    .img(PATH + equipment.getMainImg())
                    .retCnt(rentalService.findByEquipmentCnt(equipmentName))
                    .leftCnt(equipment.getCount())
                    .build();
            responses.add(response);
        }

        model.addAttribute("pages", equipmentPage);
        model.addAttribute("equipmentList", responses);
        return "fragments/equipment";
    }

}
