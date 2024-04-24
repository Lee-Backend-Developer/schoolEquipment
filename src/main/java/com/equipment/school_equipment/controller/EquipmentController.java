package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/requipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @RequestMapping
    public String equipment(Model model) {
        List<Equipment> equipmentAll = equipmentService.findAll();
        model.addAttribute(equipmentAll);
        return "requirements";
    }

}
