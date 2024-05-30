package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.request.admin.EquipmentEditRequest;
import com.equipment.school_equipment.request.admin.EquipmentForm;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import com.equipment.school_equipment.response.thymeleaf.EquipmentRequest;
import com.equipment.school_equipment.service.SecondaryCategoryService;
import com.equipment.school_equipment.service.EquipmentService;
import com.equipment.school_equipment.service.RentalService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/equipment")
public class AdminEquipmentController {

    public static String UPLOAD_DIRECTORY = "/Users/leemac/IdeaProjects/img/" + "equipment";

    private final EquipmentService equipmentService;
    private final RentalService rentalService;
    private final SecondaryCategoryService secondaryCategoryService;

    @GetMapping
    public String adminEquipments(Model model,
                                  @RequestParam(defaultValue = "0", required = false) int page,
                                  @RequestParam(defaultValue ="", required = false) String category) {
        Page<Equipment> equipmentPage;

        if(Objects.isNull(category)){
            equipmentPage = equipmentService.findAll(page, 10);
        } else {
            equipmentPage = equipmentService.findByCategoryId(category, page);
        }

        List<EquipmentRequest> equipmentRequestList = equipmentPage.stream().map(equipment -> EquipmentRequest
                        .builder()
                        .id(equipment.getId())
                        .equipmentName(equipment.getName())
                        .leftCnt(equipment.getCount())
                        .retCnt(rentalService.findByEquipmentCnt(equipment.getName()))
                        .build())
                .toList();

        model.addAttribute("pages", equipmentPage);
        model.addAttribute("equipments", equipmentRequestList);
        return "/admin/equipment/find-all";
    }

    @GetMapping("/edit/{equipmentId}")
    public String edit(@PathVariable("equipmentId") Long equipmentId, Model model) {
        Equipment equipment = equipmentService.findById(equipmentId);
        EquipmentForm form = EquipmentForm.builder()
                .equipmentId(equipment.getId())
                .name(equipment.getName())
                .content(equipment.getContent())
                .count(equipment.getCount())
                .imageName(equipment.getMainImg())
                .categoryName(equipment.getSecondaryCategory().getCategoryName())
                .categoryId(equipment.getSecondaryCategory().getId())
                .categories(secondaryCategoryService.findAll()).build();

        model.addAttribute("requestForm", form);

        return "/admin/equipment/edit";
    }

    @PostMapping("/edit/{equipmentId}")
    public String edit(@Valid @ModelAttribute("requestForm") EquipmentForm requestForm, BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors()) {
            requestForm.setCategories(secondaryCategoryService.findAll());
            requestForm.setCategoryId(equipmentService.findById(requestForm.getEquipmentId())
                    .getSecondaryCategory()
                    .getId());
            model.addAttribute("requestForm", requestForm);
            return "/admin/equipment/edit";
        }

        EquipmentEditRequest request = EquipmentEditRequest.builder()
                .id(requestForm.getEquipmentId())
                .mainImg(requestForm.getImageName())
                .count(requestForm.getCount())
                .name(requestForm.getName())
                .content(requestForm.getContent())
                .categoryId(requestForm.getSecondaryCategory().getId())
                .build();

        equipmentService.updateEquipment(request);
        return "redirect:/admin/equipment";
    }

    @GetMapping("/add")
    public String add(Model model) {
        EquipmentForm requestForm = EquipmentForm
                .builder()
                .categories(secondaryCategoryService.findAll())
                .build();

        model.addAttribute("requestForm", requestForm);
        return "/admin/equipment/add";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("requestForm") EquipmentForm requestForm, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            requestForm.setCategories(secondaryCategoryService.findAll());
            model.addAttribute("requestForm", requestForm);

            return "/admin/equipment/add";
        }
        // 이미지 파일 추가
        String fileContentType = Objects.requireNonNull(requestForm.getImage().getOriginalFilename()).split("\\.")[0];
        String fileName = UUID.randomUUID() + "." + fileContentType;

        Path path = Paths.get(UPLOAD_DIRECTORY, fileName);   // 절대경로, 이미지 저장할 이름
        Files.write(path, requestForm.getImage().getBytes());   // path 경로에 이미지 저장

        EquipmentCreate requestCreate = EquipmentCreate.builder().name(requestForm.getName())
                .count(requestForm.getCount()).equimentContent(requestForm.getContent())
                .categoryId(requestForm.getSecondaryCategory().getId())
                .image(fileName).build();
        equipmentService.save(requestCreate);
        return "redirect:/admin/equipment";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        List<Equipment> equipments = equipmentService.findAll(1, 10).toList();
        List<EquipmentRequest> equipmentRequestList = equipments.stream().map(equipment -> EquipmentRequest
                        .builder()
                        .id(equipment.getId())
                        .equipmentName(equipment.getName())
                        .leftCnt(equipment.getCount())
                        .retCnt(rentalService.findByEquipmentCnt(equipment.getName()))
                        .build())
                .toList();

        model.addAttribute("equipments", equipmentRequestList);
        return "/admin/equipment/delete";
    }

    @GetMapping("/delete/{equipmentId}")
    public void delete(@PathVariable(name = "equipmentId") Long equipmentId, HttpServletResponse response) throws IOException {
        equipmentService.delete(equipmentId);
        response.sendRedirect("/admin/equipment");
    }
}
