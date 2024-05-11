package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.admin.RentalAddRequest;
import com.equipment.school_equipment.repository.dto.RentalDuplication;
import com.equipment.school_equipment.service.RentalService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<Rental> rentals = rentalService.findByDuplication();
        model.addAttribute("rentals", rentals);
        return "admin/rental/find";
    }

    @GetMapping("/add")
    public String add(Model model) {
        RentalAddRequest request = RentalAddRequest.builder()
                .categories(categoryRepository.findAll())
                .equipments(equipmentRepository.findAll())
                .classtimes(classTimeRepository.findAll())
                .weekday(DayOfWeekEnum.values())
                .build();
        model.addAttribute("rental", request);

        return "admin/rental/add";
    }

    @PostMapping("/add")
    public void add(@ModelAttribute RentalAddRequest request, HttpServletResponse response) throws IOException {
//        rentalService.rentalCreate(request);
//        response.sendRedirect("/admin/rental/add");

    }

    // Method to check if the rental is duplicated
}
