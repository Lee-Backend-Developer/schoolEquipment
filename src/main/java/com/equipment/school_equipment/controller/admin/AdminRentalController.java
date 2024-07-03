package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.PrimaryCategoryRepository;
import com.equipment.school_equipment.repository.SecondaryCategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.admin.RentalAddRequest;
import com.equipment.school_equipment.response.thymeleaf.admin.RentalFindAllResponse;
import com.equipment.school_equipment.service.PrimaryCategoryService;
import com.equipment.school_equipment.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/rental")
public class AdminRentalController {
    private final RentalService rentalService;
    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final EquipmentRepository equipmentRepository;
    private final PrimaryCategoryService primaryCategoryService;
    private final PrimaryCategoryRepository primaryCategoryRepository;
    private final ClassTimeRepository classTimeRepository;

    @GetMapping
    public String find(Model model) {

        List<RentalFindAllResponse> responses = rentalService.findByAll().stream().map(rental ->
                RentalFindAllResponse.builder()
                        .id(rental.getId())
                        .week(rental.getClassPeriod().getDayOfWeek().getWeek())
                        .className(rental.getClassPeriod().getClassName())
                        .equipmentName(rental.getEquipment().getName())
                        .rentalChk(rental.isRentalChk())
                        .rentalCnt(rental.getRentalCnt())
                        .build()
        ).toList();

        model.addAttribute("rentals", responses);

        return "admin/rental/find-all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        RentalAddRequest form = RentalAddRequest.builder()
                .primaryCategoryList(primaryCategoryService.findAll())
                .build();
        model.addAttribute("rental", form);

        return "admin/rental/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("rental") RentalAddRequest request, BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            request.setPrimaryCategoryList(primaryCategoryRepository.findAll());
            request.setEquipmentList(equipmentRepository.findAll());
            model.addAttribute("rental", request);
            return "admin/rental/add";
        }
        rentalService.rentalCreate(request);
        return "redirect:/admin/rental";

    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        Rental request = rentalService.findById(id);

        RentalAddRequest requestForm = RentalAddRequest.builder()
                .primaryCategoryList(primaryCategoryRepository.findAll())
                .primaryCategory(request.getEquipment().getSecondaryCategory().getPrimaryCategory())
                .secondaryCategoryList(secondaryCategoryRepository.findAll())
                .secondaryCategory(request.getEquipment().getSecondaryCategory())
                .equipmentList(equipmentRepository
                        .findByEquipmentAndPrimaryCategoryAndSecondaryCategory(request.getEquipment().getSecondaryCategory().getPrimaryCategory().getId(),request.getEquipment()
                                .getSecondaryCategory().getId()))
                .equipment(request.getEquipment())
                .retCnt(request.getRentalCnt())
                .classtimeList(classTimeRepository.findByDayOfWeekEquals(request.getClassPeriod().getDayOfWeek()))
                .classPeriod(request.getClassPeriod())
                .weekday(request.getClassPeriod().getDayOfWeek())
                .build();


        model.addAttribute("rental", requestForm);
        return "admin/rental/find";
    }

    @PutMapping("/edit/{id}")
    public String putEditPage(@PathVariable Long id, RentalAddRequest requestForm){
        log.info("request form => {}", requestForm);

        Rental updateRental = Rental.builder()
                .id(id)
                .equipment(requestForm.getEquipment())
                .classPeriod(requestForm.getClassPeriod())
                .rentalCnt(requestForm.getRetCnt())
                .build();

        rentalService.rentalUpdate(updateRental);
        return "redirect:/admin/rental";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        rentalService.rentaldelete(id);
        return "redirect:/admin/rental";
    }
}
