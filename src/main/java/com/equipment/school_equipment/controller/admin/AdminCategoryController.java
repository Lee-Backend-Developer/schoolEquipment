package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.request.admin.CategoryEditResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.CategoryFindResponse;
import com.equipment.school_equipment.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/add")
    public String addCategory(@ModelAttribute("category") CategoryAddRequest request) {
        return "admin/category/categoryAdd";
    }

    @PostMapping("/add")
    public void createCategory(@ModelAttribute("category") CategoryAddRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request);
        categoryService.addCategory(request.name());

        response.sendRedirect("/admin/category/find");
    }

    @GetMapping("/find")
    public String findCategory(Model model) {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryFindResponse> categoryRespons = categoryList.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("categorys", categoryRespons);
        return "admin/category/categoryFindAll";
    }

    @GetMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);

        CategoryEditResponse response = CategoryEditResponse.builder().id(category.getId()).oldClassname(category.getCategoryName()).build();
        model.addAttribute("category", response);
        return "admin/categoryEdit";
    }

    @PostMapping("/edit/{categoryId}")
    public void editCategory(@PathVariable("categoryId") Long categoryId, @ModelAttribute("category") CategoryEditResponse editResponse, HttpServletResponse response) throws IOException {
        categoryService.findByIdAndName(categoryId, editResponse.oldClassname(), editResponse.changeNameClassname());
        response.sendRedirect("/admin/category/find");
    }

    @GetMapping("/delete")
    public String deleteCategory(Model model) {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryFindResponse> categoryRespons = categoryList.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("categorys", categoryRespons);
        return "admin/category/categoryDelete";
    }

    @GetMapping("/delete/{categoryid}")
    public void deleteCategory(@PathVariable("categoryid") Long categoryid, HttpServletResponse response) throws IOException {
        categoryService.deleteById(categoryid);

        response.sendRedirect("/admin/category/delete");
    }
}
