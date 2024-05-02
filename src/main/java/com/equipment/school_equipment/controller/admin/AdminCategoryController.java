package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.request.admin.CategoryAddRequest;
import com.equipment.school_equipment.response.thymeleaf.admin.CategoryFindResponse;
import com.equipment.school_equipment.service.CategoryService;
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

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminCategoryController {
    /**
     * todo 카테고리 추가
     * todo 카테고리 모두 읽기
     * todo 카테고리 이름 수정
     * todo 카테고리 삭제
     */

    private final CategoryService categoryService;

    @GetMapping("/add")
    public String addCategory(@ModelAttribute("category") CategoryAddRequest request) {
        return "admin/categoryAdd";
    }

    @PostMapping("/add")
    public void createCategory(@ModelAttribute("category") CategoryAddRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request);
        categoryService.addCategory(request.name());

        response.sendRedirect("/");
    }

    @GetMapping("/find")
    public String findCategory(Model model) {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryFindResponse> categoryFindResponses = categoryList.stream()
                .map(category -> CategoryFindResponse.builder().id(category.getId()).name(category.getCategoryName()).build())
                .toList();

        model.addAttribute("categorys", categoryFindResponses);
        return "admin/categoryFindAll";
    }
}
