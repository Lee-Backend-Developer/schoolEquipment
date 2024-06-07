package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.request.admin.CategoryEditResponse;
import com.equipment.school_equipment.service.PrimaryCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminPrimaryCategoryController {

    private final PrimaryCategoryService primaryCategoryService;

    @GetMapping
    private String findAllPaging(Model model,
                                 @RequestParam(defaultValue = "0", required = false) int page) {
        Page<PrimaryCategory> primaryCategoryPage = primaryCategoryService.findAllPage(page);
        model.addAttribute("primaryCategoryPage", primaryCategoryPage);
        return "admin/category/primary/find-all";
    }

    @GetMapping("/add")
    private String getAddPrimaryCategory(@ModelAttribute("form") CategoryAddRequest formRequest) {
        return "admin/category/primary/add";
    }

    @PostMapping("add")
    private String postAddPrimaryCategory(@ModelAttribute("form") CategoryAddRequest formRequest) {
        PrimaryCategory primaryCategoryBuild = PrimaryCategory.builder().categoryName(formRequest.name()).build();
        primaryCategoryService.add(primaryCategoryBuild);
        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{primary_category_id}")
    private String getEditPrimaryCategory(
            @PathVariable(name = "primary_category_id") Long primaryCategoryId, Model model){
        PrimaryCategory primaryCategory = primaryCategoryService.findById(primaryCategoryId);

        CategoryEditResponse categoryEditResponse = CategoryEditResponse.builder()
                .categoryId(primaryCategory.getId())
                .oldClassname(primaryCategory.getCategoryName())
                .build();

        model.addAttribute("category", categoryEditResponse);

        return "admin/category/primary/edit";
    }

    @PostMapping("/edit/{categoryId}")
    private String postEditPrimaryCategory(@Valid @ModelAttribute("category") CategoryEditResponse categoryEditResponse, BindingResult bindingResult, Model model){
        if(bindingResult.hasFieldErrors()) {return "admin/category/primary/edit";}
        primaryCategoryService.changeCategoryName(categoryEditResponse.categoryId(), categoryEditResponse.changeNameClassname());

        return "redirect:/admin/category";

    }

    @GetMapping("/delete/{primary_category_id}")
    private String getDeletePrimaryCategory(
            @PathVariable(name = "primary_category_id") long primaryCategoryId){
        primaryCategoryService.deleteById(primaryCategoryId);
        return "redirect:/admin/category";
    }
}
