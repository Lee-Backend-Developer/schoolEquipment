package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.request.admin.EquipmentAddRequest;
import com.equipment.school_equipment.request.admin.EquipmentEditRequest;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import com.equipment.school_equipment.response.thymeleaf.EquipmentResponse;
import com.equipment.school_equipment.service.CategoryService;
import com.equipment.school_equipment.service.EquipmentService;
import com.equipment.school_equipment.service.RentalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/equipment")
public class AdminEquipmentController {
    private final EquipmentService equipmentService;
    private final RentalService rentalService;
    private final CategoryService categoryService;

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

    @GetMapping("/edit/{equipmentId}")
    public String edit(@PathVariable("equipmentId") Long equipmentId, Model model) {

        List<Category> categorys = categoryService.findAll();
        Equipment equipment = equipmentService.findById(equipmentId);

        model.addAttribute("equipment", equipment);
        model.addAttribute("categorys", categorys);

        return "/admin/equipment/equipmentEdit";
    }

    @PostMapping("/edit/{equipmentId}")
    public void edit(@PathVariable("equipmentId") Long equipmentId, @ModelAttribute Equipment equipment, @ModelAttribute(name = "categorys") Category category, HttpServletResponse response) throws IOException {
        EquipmentEditRequest request = EquipmentEditRequest.builder()
                .id(equipmentId)
                .mainImg(equipment.getMainImg())
                .count(equipment.getCount())
                .name(equipment.getName())
                .content(equipment.getContent())
                .categoryId(category.getId()).build();
        equipmentService.updateEquipment(request);
        response.sendRedirect("/admin/equipment");

    }

    @GetMapping("/add")
    public String add(Model model) {
        EquipmentAddRequest addRequest = EquipmentAddRequest.builder().build();
        List<Category> categorys = categoryService.findAll();

        model.addAttribute("equipment", addRequest);
        model.addAttribute("categorys", categorys);
        return "/admin/equipment/equipmentAdd";
    }

    @PostMapping("/add")
    public void add(@ModelAttribute EquipmentAddRequest addRequest, @ModelAttribute(name = "categorys") Category category, HttpServletResponse response) throws IOException {
        EquipmentCreate requestCreate = EquipmentCreate.builder().name(addRequest.name()).count(addRequest.count()).categoryId(category.getId()).build();
        equipmentService.save(requestCreate);
        response.sendRedirect("/admin/equipment");
    }

    @GetMapping("/delete")
    public String delete(Model model) {
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
        return "/admin/equipment/equipmentDelete";
    }

    @GetMapping("/delete/{equipmentId}")
    public void delete(@PathVariable(name = "equipmentId") Long equipmentId, HttpServletResponse response) throws IOException {
        equipmentService.delete(equipmentId);
        response.sendRedirect("/admin/equipment/delete");
    }
}
