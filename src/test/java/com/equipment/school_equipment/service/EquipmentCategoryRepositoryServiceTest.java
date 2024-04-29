package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.EquipmentCategory;
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
class EquipmentCategoryRepositoryServiceTest {
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
        EquipmentCategory equipmentCategory = equipmentCategoryRepository.findByCategoryName(name).get();
        assertThat(equipmentCategory.getCategoryName()).isEqualTo(name);

    }

    @DisplayName("카메라, 마이크 이 두개가 조회가 되어야 한다")
    @Test
    void findCategory_O(){
        //given
        EquipmentCategory camara = EquipmentCategory.builder().categoryName("카메라").build();
        EquipmentCategory mic = EquipmentCategory.builder().categoryName("마이크").build();
        equipmentCategoryRepository.save(camara);
        equipmentCategoryRepository.save(mic);

        //when

        List<EquipmentCategory> equipmentCategoryList = equipmentCategoryService.findAll();
        //then
        assertThat(equipmentCategoryList).hasSize(2);
        assertThat(equipmentCategoryList).contains(camara, mic);

    }
}