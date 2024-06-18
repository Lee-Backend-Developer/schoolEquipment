package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.request.admin.CategoryEditResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.CategoryFindResponse;
import com.equipment.school_equipment.service.SecondaryCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/primary-category/{primaryCategory}")
public class AdminSecondaryCategoryController {

    private final SecondaryCategoryService secondaryCategoryService;

    @GetMapping("/add")
    public String getAddCategory(@ModelAttribute("category") CategoryAddRequest request, @PathVariable String primaryCategory) {
        return "admin/category/secondary/add";
    }

    @PostMapping("/add")
    public String postAddCategory(@Valid @ModelAttribute("category") CategoryAddRequest request,
                                  BindingResult bindingResult,
                                  @PathVariable String primaryCategory) {
        if (bindingResult.hasErrors()) {
            return "admin/category/secondary/add";
        }

        secondaryCategoryService.addCategory(primaryCategory, request.name());

        return "redirect:/admin/category/{primaryCategory}";
    }

    @GetMapping
    public String findCategory(@PathVariable String primaryCategory, //@RequestParam(defaultValue = "0", required = false) int page
                               Model model) {

        List<SecondaryCategory> byPrimaryCategory = secondaryCategoryService.findByPrimaryCategory(primaryCategory);
        List<CategoryFindResponse> categoryRespons = byPrimaryCategory.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("secondaryCategory", primaryCategory);
//        model.addAttribute("pages", categoryPage);
        model.addAttribute("categories", categoryRespons);
        return "admin/category/secondary/find-all";
    }

    @GetMapping("/edit/{categoryId}")
    public String getEditCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        SecondaryCategory secondaryCategory = secondaryCategoryService.findById(categoryId);

        CategoryEditResponse form = CategoryEditResponse
                .builder().categoryId(secondaryCategory.getId())
                .oldClassname(secondaryCategory.getCategoryName()).build();
        model.addAttribute("categoryForm", form);
        return "admin/category/secondary/edit";
    }

    @PostMapping("/edit/{categoryId}")
    public String getEditCategory(@Valid @ModelAttribute("category") CategoryEditResponse request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/secondary/edit";
        }
        secondaryCategoryService.findByIdAndName(request.categoryId(), request.oldClassname(), request.changeNameClassname());
        return "redirect:/admin/category/{primaryCategory}";

    }

    @GetMapping("/delete/{categoryid}")
    public String getDeleteCategory(@PathVariable("categoryid") Long categoryId) {
        secondaryCategoryService.deleteById(categoryId);

        return "redirect:/admin/category/{primaryCategory}";
    }
}
