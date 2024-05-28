package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.request.admin.CategoryEditResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.CategoryFindResponse;
import com.equipment.school_equipment.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category/{primaryCategory}")
public class AdminSecondaryCategoryController {

    private static final Logger log = LoggerFactory.getLogger(AdminSecondaryCategoryController.class);
    private final CategoryService categoryService;

    @GetMapping("/add")
    public String addCategory(@ModelAttribute("category") CategoryAddRequest request) {
        return "admin/category/add";
    }

    @PostMapping("/add")
    public String createCategory(@Valid @ModelAttribute("category") CategoryAddRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/add";
        }

        categoryService.addCategory(request.name());

        return "redirect:/admin/category";
    }

    @GetMapping
    public String findCategory(Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        Page<SecondaryCategory> categoryPage = categoryService.findAll(page);
        List<CategoryFindResponse> categoryRespons = categoryPage.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("pages", categoryPage);
        model.addAttribute("categorys", categoryRespons);
        return "admin/category/find-all";
    }

    @GetMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        SecondaryCategory secondaryCategory = categoryService.findById(categoryId);

        CategoryEditResponse request = CategoryEditResponse.builder().categoryId(secondaryCategory.getId()).oldClassname(secondaryCategory.getCategoryName()).build();
        model.addAttribute("category", request);
        return "admin/category/edit";
    }

    @PostMapping("/edit/{categoryId}")
    public String editCategory(@Valid @ModelAttribute("category") CategoryEditResponse request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/edit";
        }
        categoryService.findByIdAndName(request.categoryId(), request.oldClassname(), request.changeNameClassname());
        return "redirect:/admin/category";
    }

    @GetMapping("/delete")
    public String deleteCategory(Model model) {
        List<SecondaryCategory> secondaryCategoryList = categoryService.findAll(1).toList();
        List<CategoryFindResponse> categoryRespons = secondaryCategoryList.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("categorys", categoryRespons);
        return "admin/category/delete";
    }

    @GetMapping("/delete/{categoryid}")
    public String deleteCategory(@PathVariable("categoryid") Long categoryId) {
        categoryService.deleteById(categoryId);

        return "redirect:/admin/category";
    }
}
