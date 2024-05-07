package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import com.equipment.school_equipment.request.equipment.EquipmentCount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class EquipmentServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentService equipmentService;


    @BeforeEach
    void setUp() {
        Category category = new Category("카메라");
        categoryRepository.save(category);

        Equipment equipment = Equipment.builder().name("pmw-200").count(10).build();
        equipmentRepository.save(equipment);
        equipment.addCategory(category);
    }

    @AfterEach
    void end() {
        equipmentRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @DisplayName("장비이름,장비수량이 저장이 되어야함")
    @Test
    void save_O() {
        equipmentRepository.deleteAll();
        categoryRepository.deleteAll();
        //given
        Category category = new Category("카메라");
        categoryRepository.save(category);

        EquipmentCreate request = EquipmentCreate.builder()
                .name("pmw-200")
                .count(10)
                .categoryId(category.getId())
                .build();

        //when
        Equipment saveEquipment = equipmentService.save(request);
        saveEquipment.addCategory(category);


        //then
        assertThat(equipmentRepository.count()).isEqualTo(1);
    }

    @DisplayName("현재 존재하는 장비를 조회함")
    @Test
    void findAll() {
        //given
        Category category = new Category("카메라");
        categoryRepository.save(category);

        Equipment saveEquipment1 = equipmentRepository.save(Equipment.builder().name("pmw-200").count(10).build());
        Equipment saveEquipment2 = equipmentRepository.save(Equipment.builder().name("pmw-300").count(10).build());

        saveEquipment1.addCategory(category);
        saveEquipment2.addCategory(category);
        //when
        List<Equipment> equipments = equipmentService.findAll();

        //then
        assertThat(equipments.size()).isEqualTo(3);
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

    @DisplayName("장비가 삭제가 되어야한다")
    @Test
    void delete_o(){
        //given
        Equipment equipment = Equipment.builder().name("pmw-200").count(10).build();
        equipmentRepository.save(equipment);

        //when
        equipmentService.delete(equipment.getId());
        //then
        assertThat(equipmentRepository.findById(equipment.getId())).isEmpty();
    }
}