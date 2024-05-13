package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.admin.RentalAddRequest;
import com.equipment.school_equipment.response.thymeleaf.admin.RentalFindAllResponse;
import com.equipment.school_equipment.service.RentalService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/rental")
public class AdminRentalController {
    private final RentalService rentalService;
    private final CategoryRepository categoryRepository;
    private final ClassTimeRepository classTimeRepository;
    private final EquipmentRepository equipmentRepository;

    @GetMapping
    public String find(Model model) {

        List<RentalFindAllResponse> responses = rentalService.findByAll().stream().map(rental ->
                RentalFindAllResponse.builder()
                        .id(rental.getId())
                        .equipmentName(rental.getEquipmentId().getName())
                        .className(rental.getClasstimesId().getClassName())
                        .rentalChk(rental.isRentalChk())
                        .rentalCnt(rental.getRentalCnt())
                        .build()
        ).toList();

        model.addAttribute("rentals", responses);

        return "admin/rental/find";
    }

    @GetMapping("/add")
    public String add(Model model) {
        RentalAddRequest request = RentalAddRequest.builder()
                .categories(categoryRepository.findAll())
                .equipments(equipmentRepository.findAll())
                .weekday(DayOfWeekEnum.values())
                .build();
        model.addAttribute("rental", request);

        return "admin/rental/add";
    }

    @PostMapping("/add")
    public void add(@ModelAttribute("rental") RentalAddRequest request, HttpServletResponse response) throws IOException {
        rentalService.rentalCreate(request);
        response.sendRedirect("/admin/rental");

    }

    @GetMapping("/delete/{id}")
    public String edit(@PathVariable Long id) {
        rentalService.rentaldelete(id);
        return "redirect:/admin/rental";
    }
}
