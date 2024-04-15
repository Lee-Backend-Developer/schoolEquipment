package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EquipmentServiceTest {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentService equipmentService;

    @DisplayName("장비이름,장비수량이 저장이 되어야함")
    @Test
    void save_O(){
        //given
        EquipmentCreate request = EquipmentCreate.builder()
                .name("pmw-200")
                .count(10)
                .build();

        //when
        equipmentService.save(request);

        //then
        assertThat(equipmentRepository.count()).isEqualTo(1);
    }

    @DisplayName("장비를 빌렸을때 빌린 만큼 빠져야한다")
    @Test
    void update_O(){
        //given
        EquipmentCreate request = EquipmentCreate.builder()
                .name("pmw-200")
                .count(10)
                .build();

        //when
        Equipment saveEquipment = equipmentService.save(request);
        equipmentService.countUpdate("pmw-200",1);

        //then
        assertThat(saveEquipment.getCount()).isEqualTo(9);

    }
}