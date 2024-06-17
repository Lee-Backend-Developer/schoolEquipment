package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.response.thymeleaf.RentalEquipmentResponse;
import com.equipment.school_equipment.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    @RequestMapping("/{dayOfWeek}/{classnameId}")
    public String day(@PathVariable(name = "dayOfWeek") String dayofweek, @PathVariable(name = "classnameId") String classname, Model model){
        List<Equipment> equipments = rentalService.findByClassnameIdAndDayOfWeek(classname, dayofweek);
        List<RentalEquipmentResponse> rentalEquipmentResponse = new ArrayList<>();

        for (Equipment equipment : equipments) {
            int leftCnt = rentalService.findByEquipmentCnt(equipment.getName());
            RentalEquipmentResponse rentalEquipment = RentalEquipmentResponse.builder()
                    .name(equipment.getName())
                    .image(equipment.getMainImg())
                    .content(equipment.getContent())
                    .retCnt(equipment.getCount())
                    .leftCnt(leftCnt)
                    .build();
            rentalEquipmentResponse.add(rentalEquipment);
        }
        model.addAttribute("rentalEquipment", rentalEquipmentResponse);
        return "retalClassPage";
    }
}
