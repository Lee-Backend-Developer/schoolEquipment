package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.dto.TodayRentalSelectDto;
import com.equipment.school_equipment.response.thymeleaf.EquipmentResponse;
import com.equipment.school_equipment.service.EquipmentService;
import com.equipment.school_equipment.service.PrimaryCategoryService;
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
import java.util.Objects;

import static java.util.Objects.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("equipment")
public class EquipmentController {
    private final String PATH = "/equipment/";

    private final EquipmentService equipmentService;
    private final PrimaryCategoryService primaryCategoryService;
    private final RentalService rentalService;


    // 대여후 수량(가지고 있던 수량 - 렌탈마다 장비 수)
    // 응답: 장비이름, 대여 후 수량, 가지고 있던 주량
    @GetMapping
    public String equipment(Model model,
                            @RequestParam(defaultValue = "0", required = false) int page,
                            @RequestParam(required = false) String category) {

        /*Page<Equipment> equipmentPage = (isNull(category)) ?
                equipmentService.findAll(page, 15) :
                equipmentService.findByCategoryId(category, page);*/

        Page<TodayRentalSelectDto> byEquipmentJoinToday = rentalService.findByEquipmentJoinToday(page, 15);


        List<EquipmentResponse> responses = byEquipmentJoinToday.getContent()
                .stream().map(equipment -> EquipmentResponse.builder()
                        .equipmentName(equipment.name())
                        .img(PATH + equipment.mainImg())
                        .retCnt(isNull(equipment.rentalInt()) ? 0 : equipment.rentalInt())
                        .leftCnt(equipment.equipmentCnt())
                        .build())
                .toList();

        model.addAttribute("pages", byEquipmentJoinToday);
        model.addAttribute("equipmentList", responses);
        model.addAttribute("primaryCategories", primaryCategoryService.findAll());
        return "equipment";
    }

}
