package com.equipment.school_equipment.service;

import com.equipment.school_equipment.repository.EquipmentCategory;
import com.equipment.school_equipment.repository.EquipmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EquipmentCategoryServiceTest {
    @Autowired
    private EquipmentCategory equipmentRepository;
    @Autowired
    private EquipmentCategoryService equipmentCategoryService;

    @DisplayName("카테고리가 생성이 되어야한다")
    @Test
    void create_O(){
        //given

        //when
        equipmentCategoryService.add(String name);
        //then

    }
}