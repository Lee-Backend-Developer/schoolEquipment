package com.equipment.school_equipment.service;

import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RentalServiceTest {
    @Autowired
    private ClassTimeRepository classTimeRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private RentalService rentalService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void end() {

    }

    @DisplayName("장비를 대여가 되고 장비도 대어한 만큼 줄어들어야함")
    @Test
    void equipmentRental() {
        //given
//        new RentalCreateDto(new ClassTime(null), new Equipment(null, null));

        //when
//        rentalService.rentalCreate(RentalCreateDto dto);

        //then
        assertThat(equipmentRepository.count()).isEqualTo(0);
    }
}
