package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.PrimaryCategoryRepository;
import com.equipment.school_equipment.repository.SecondaryCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class EquipmentSecondaryCategoryTest {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private PrimaryCategoryRepository primaryCategoryRepository;
    @Autowired
    private SecondaryCategoryRepository secondaryCategoryRepository;

    @AfterEach
    void end() {
        equipmentRepository.deleteAll();
        primaryCategoryRepository.deleteAll();
    }

    @DisplayName("pmw장비가 카메라 하위 카테고리에 추가가 되어야한다.")
    @Test
    void addEquipment_O() throws Exception {
        //given 준비 과정
        SecondaryCategory saveCamara = SecondaryCategory.builder().categoryName("카메라").build();
        secondaryCategoryRepository.save(saveCamara);


        Equipment saveEquipment = Equipment.builder().name("pmw").build();
        equipmentRepository.save(saveEquipment);

        saveEquipment.addCategory(saveCamara);

        //when 실행

        //then 검증
        assertThat(saveEquipment.getSecondaryCategory().getCategoryName()).isEqualTo(saveCamara.getCategoryName());
        assertThat(saveCamara.getEquipmentList().get(0)).isEqualTo(saveEquipment);

    }
}
