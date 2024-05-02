package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.repository.EquipmentCategoryRepository;
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
    private EquipmentCategoryService equipmentCategoryService;

    @DisplayName("카메라 라는 카테고리가 만들어져야한다")
    @Test
    void create_O(){
        //given
        String name = "카메라";

        //when
        equipmentCategoryService.addEquipmentCategory(name);

        //then
        Category category = equipmentCategoryRepository.findByCategoryName(name).get();
        assertThat(category.getCategoryName()).isEqualTo(name);

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

        List<Category> categoryList = equipmentCategoryService.findAll();
        //then
        assertThat(categoryList).hasSize(2);
        assertThat(categoryList).contains(camara, mic);

    }
}