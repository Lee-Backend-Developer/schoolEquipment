package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.repository.PrimaryCategoryRepository;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.service.PrimaryCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminPrimaryCategoryController {

    private final PrimaryCategoryService primaryCategoryService;

    @GetMapping
    private String findAllPaging(Model model) {
        List<PrimaryCategory> primaryCategoryList = primaryCategoryService.findAll();
        model.addAttribute("primaryCategoryList", primaryCategoryList);
        return "admin/category/primary/find-all";
    }

    @GetMapping("/add")
    private String getAddPrimaryCategory(@ModelAttribute("form") CategoryAddRequest formRequest) {
        return "admin/category/primary/add";
    }

    @PostMapping("/add")
    private String postAddPrimaryCategory(@ModelAttribute("form") CategoryAddRequest formRequest) {
        PrimaryCategory primaryCategoryBuild = PrimaryCategory.builder().categoryName(formRequest.name()).build();
        primaryCategoryService.add(primaryCategoryBuild);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{primary_category_id}")
    private String getDeletePrimaryCategory(
            @PathVariable(name = "primary_category_id") long primaryCategoryId){
        primaryCategoryService.deleteById(primaryCategoryId);
        return "redirect:/admin/category";
    }
}
