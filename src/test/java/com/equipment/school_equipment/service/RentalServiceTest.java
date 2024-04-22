package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.RentalRepository;
import com.equipment.school_equipment.request.rental.RentalCreate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class RentalServiceTest {
    @Autowired
    private ClassTimeRepository classTimeRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        equipmentRepository.save(new Equipment("pmw", 10));
        classTimeRepository.save(ClassTime.builder().className("영상촬영실습").twoTime(true).threeTime(true).fourTime(true).build());
    }

    @AfterEach
    void end() {
        equipmentRepository.deleteAll();
        classTimeRepository.deleteAll();
        rentalRepository.deleteAll();
    }

    @DisplayName("장비를 대여가 되고 장비도 대어한 만큼 줄어들어야함")
    @Test
    void equipmentRental() {
        //given

        RentalCreate request = new RentalCreate("영상촬영실습", "pmw", 2);

        //when
        rentalService.rentalCreate(request);

        //then
        //수량확인
        Equipment findEquipment = equipmentRepository.findByName("pmw");
        assertThat(findEquipment.getCount()).isEqualTo(8);
        assertThat(equipmentRepository.count()).isEqualTo(1);
    }
}
