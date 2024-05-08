package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.request.rental.RentalCreate;
import com.equipment.school_equipment.request.rental.RentalReturn;
import com.equipment.school_equipment.response.thymeleaf.EquipmentResponse;
import com.equipment.school_equipment.service.EquipmentService;
import com.equipment.school_equipment.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("requipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final RentalService rentalService;
    final String PATH = "/equipment/";


    // 대여후 수량(가지고 있던 수량 - 렌탈마다 장비 수)
    // 응답: 장비이름, 대여 후 수량, 가지고 있던 주량
    @RequestMapping
    public String equipment(Model model) {
        List<EquipmentResponse> responses = new ArrayList<>();
        for (Equipment equipment : equipmentService.findAll()) {
            String equipmentName = equipment.getName();
            EquipmentResponse response = EquipmentResponse.builder()
                    .equipmentName(equipmentName)
                    .content(equipment.getContent())
                    .img(PATH + equipment.getMainImg())
                    .retCnt(rentalService.findByEquipmentCnt(equipmentName))
                    .leftCnt(equipment.getCount())
                    .build();
            responses.add(response);
        }
        model.addAttribute(responses);
        return "requirements";
    }

}
