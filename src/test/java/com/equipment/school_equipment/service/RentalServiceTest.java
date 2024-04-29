package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassTimeList;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.RentalRepository;
import com.equipment.school_equipment.request.rental.RentalCreate;
import com.equipment.school_equipment.request.rental.RentalReturn;
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
        classTimeRepository.save(ClassTimeList.builder()
                .className("영상촬영실습").dayOfWeek(DayOfWeekEnum.Monday)
                .twoTime(true).threeTime(true).fourTime(true).build());
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

        RentalCreate request = RentalCreate.builder()
                .className("영상촬영실습")
                .equipmentName("pmw")
                .equipmentCount(2)
                .build();

        //when
        rentalService.rentalCreate(request);

        //then
        //수량확인
        Equipment findEquipment = equipmentRepository.findByName("pmw");
        assertThat(findEquipment.getCount()).isEqualTo(8);
        assertThat(equipmentRepository.count()).isEqualTo(1);
    }

    @DisplayName("수업명과 요일을 입력받아 장비가 몇 개 들어가는지 조회가 되어야함")
    @Test
    void classTimeRental() {
        //given
        String classname = "배우연기연출";
        String monday = DayOfWeekEnum.Monday.name();
        String equipmentName = "pmw-test";
        int maxCount = 20;
        int inputCount = 10;

        ClassTimeList saveClasstime = classTimeRepository.save(ClassTimeList.builder().className(classname)
                .dayOfWeek(DayOfWeekEnum.valueOf(monday))
                .twoTime(true).build());

        Equipment saveEquipment = equipmentRepository.save(new Equipment(equipmentName, maxCount));


        //when
        saveEquipment.editCount(inputCount);

        Rental saveRental = Rental.builder()
                .classTimeList(saveClasstime)
                .equipment(saveEquipment)
                .build();
        rentalRepository.save(saveRental);

        List<Equipment> equipments = rentalService.findByEquipment(classname, monday);

        //then
        assertThat(equipments.get(0).getName()).isEqualTo(equipmentName);
        assertThat(equipments.get(0).getCount()).isEqualTo(inputCount);

    }

    @DisplayName("장비를 반납했으면 반납한 수 만큼 더 해야져함")
    @Test
    void returnRental(){
        //given
        RentalReturn request = RentalReturn.builder().classname("영상촬영실습").equipmentName("pmw").retCount(3).build();

        //when
        rentalService.rentalReturn(request);

        //then
        assertThat(equipmentRepository.findByName("pmw").getCount()).isEqualTo(13);
    }
}
