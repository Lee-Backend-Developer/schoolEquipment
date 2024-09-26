package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.SecondaryCategoryRepository;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EquipmentServiceTest.class);
    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private SecondaryCategoryRepository secondaryCategoryRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        SecondaryCategory secondaryCategory = SecondaryCategory.builder()
                .categoryName("카메라")
                .build();
        given(secondaryCategoryRepository.findById(any()))
                .willReturn(Optional.of(secondaryCategory));
    }

    @DisplayName("기자재가 만들어져야한다.")
    @Test
    void add_O() throws Exception {
        //given 준비 과정
        given(equipmentRepository.save(any(Equipment.class)))
                .willReturn(Equipment.builder()
                .name("캐논 뭐시기")
                .count(10)
                .build());

        //when 실행
        EquipmentCreate addEquipmentRequest = EquipmentCreate.builder()
                .name("카메라")
                .count(10)
                .build();
        Equipment equipment = equipmentService.addEquipment(addEquipmentRequest);

        //then 검증
        assertEquals(equipment.getName(), "캐논 뭐시기");
        assertEquals(equipment.getCount(), 10);
    }

    @DisplayName("기자재 이름이 겹칠때 예외처리 되어야한다.")
    @Test
    void add_throw() throws Exception {
        //given 준비 과정
        given(equipmentRepository.save(any(Equipment.class)))
                .willThrow(EntityExistsException.class);

        //when 실행
        EquipmentCreate addEquipmentRequest = EquipmentCreate.builder()
                .name("캐논 뭐시기")
                .categoryId(1L)
                .count(10)
                .build();

        //then 검증
        assertThrows(EntityExistsException.class,
                () -> equipmentService.addEquipment(addEquipmentRequest)
        );
    }

}