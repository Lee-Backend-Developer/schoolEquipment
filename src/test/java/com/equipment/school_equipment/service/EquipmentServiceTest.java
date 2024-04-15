package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import com.equipment.school_equipment.request.equipment.EquipmentCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EquipmentServiceTest {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        Equipment equipment = new Equipment("pmw-200", 10);
        equipmentRepository.save(equipment);
    }

    @DisplayName("장비이름,장비수량이 저장이 되어야함")
    @Test
    void save_O() {
        equipmentRepository.deleteAll();
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
    void minus_O() {
        //given
        EquipmentCount request = new EquipmentCount("pmw-200", 1);

        //when
        equipmentService.countMinus(request);

        //then
        Equipment findEquipment = equipmentRepository.findByName("pmw-200");
        assertThat(findEquipment.getCount()).isEqualTo(9);
    }

    @DisplayName("장비를 반납했을때 반납한 만큼 더해져야함")
    @Test
    void plus_O(){
        //given
        EquipmentCount request = new EquipmentCount("pmw-200", 1);

        //when
        equipmentService.countPlus(request);

        //then
        Equipment findEquipment = equipmentRepository.findByName("pmw-200");
        assertThat(findEquipment.getCount()).isEqualTo(11);
    }
}