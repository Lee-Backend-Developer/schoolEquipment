package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.repository.EquipmentCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryRepositoryServiceTest {
    @Autowired
    private EquipmentCategoryRepository equipmentCategoryRepository;
    @Autowired
    private CategoryService categoryService;

    @DisplayName("카메라 라는 카테고리가 만들어져야한다")
    @Test
    void create_O(){
        //given
        String name = "카메라";

        //when
        categoryService.addCategory(name);

        //then
        Category category = equipmentCategoryRepository.findByCategoryName(name).get();
        assertThat(category.getCategoryName()).isEqualTo(name);

    }

    @DisplayName("카테고리 고유명인 아이디 입력 받으면 카테고리 정보가 나와야한다")
    @Test
    void findById_O() {
        //given
        Category camara = Category.builder().categoryName("카메라").build();
        equipmentCategoryRepository.save(camara);

        Long id = 1L;

        //when
        Category category = categoryService.findById(id);

        //then
        assertThat(category.getId()).isEqualTo(id);

    }

    @DisplayName("ID, 이름 입력을 받아 카테고리 이름 변경이 되어야한다.")
    @Test
    void findByIdAndName_O() throws Exception {
        //given 준비 과정
        Category category = equipmentCategoryRepository.save(Category.builder().categoryName("카메라").build());

        //when 실행
        Category findCategory = categoryService.findByIdAndName(category.getId(), category.getCategoryName(), "마이크");

        //then or expect 검증
        assertThat(findCategory.getId()).isEqualTo(category.getId());
        assertThat(findCategory.getCategoryName()).isEqualTo("마이크");
    }

    @DisplayName("카메라, 마이크 이 두개가 조회가 되어야 한다")
    @Test
    void findCategory_O(){
        //given
        Category camara = Category.builder().categoryName("카메라").build();
        Category mic = Category.builder().categoryName("마이크").build();
        equipmentCategoryRepository.save(camara);
        equipmentCategoryRepository.save(mic);

        //when

        List<Category> categoryList = categoryService.findAll();
        //then
        assertThat(categoryList).hasSize(2);
        assertThat(categoryList).contains(camara, mic);

    }
}