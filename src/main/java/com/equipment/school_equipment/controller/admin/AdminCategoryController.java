package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.request.admin.CategoryEditResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.CategoryFindResponse;
import com.equipment.school_equipment.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private static final Logger log = LoggerFactory.getLogger(AdminCategoryController.class);
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
    public String findCategory(Model model) {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryFindResponse> categoryRespons = categoryList.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getCategoryId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("categorys", categoryRespons);
        return "admin/category/find-all";
    }

    @GetMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);

        CategoryEditResponse request = CategoryEditResponse.builder().categoryId(category.getCategoryId()).oldClassname(category.getCategoryName()).build();
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
        List<Category> categoryList = categoryService.findAll();
        List<CategoryFindResponse> categoryRespons = categoryList.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getCategoryId()).name(category.getCategoryName()).build())
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
