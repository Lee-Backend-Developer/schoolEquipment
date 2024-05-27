package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class EquipmentCategoryTest {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void end() {
        equipmentRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @DisplayName("pmw장비가 카메라 카테고리에 추가가 되어야한다.")
    @Test
    void addEquipment_O() throws Exception {
        //given 준비 과정
        Category saveCamara = Category.builder().categoryName("카메라").build();
        categoryRepository.save(saveCamara);

        Equipment saveEquipment = Equipment.builder().name("pmw").build();
        equipmentRepository.save(saveEquipment);

        saveEquipment.addCategory(saveCamara);

        //when 실행

        //then 검증
        assertThat(saveEquipment.getCategory().getCategoryName()).isEqualTo(saveCamara.getCategoryName());
        assertThat(saveCamara.getEquipmentList().get(0)).isEqualTo(saveEquipment);

    }
}
