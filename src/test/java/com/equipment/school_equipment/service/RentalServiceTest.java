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
    void createRental() {
        //given
        RentalCreate request = RentalCreate.builder()
                .className("영상촬영실습")
                .dayOfWeekEnum(DayOfWeekEnum.Monday)
                .equipmentName("pmw")
                .equipmentCount(2)
                .build();

        //when
        int countLeft = rentalService.rentalCreate(request); //남은 장비 수량 ((장비 갯수[10] - 렌탈 갯수[2]) => 8

        //then
        //수량확인
        Rental findRental = rentalRepository.findAll().get(0);
        assertThat(findRental.getRentalCnt()).isEqualTo(2); // 2개를 렌탈
        assertThat(countLeft).isEqualTo(8);                 // 8개가 남아야함
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
        Rental saveRental = Rental.builder()
                .classTimeListId(saveClasstime)
                .equipmentId(saveEquipment)
                .rentalCnt(inputCount)
                .build();
        rentalRepository.save(saveRental);

        Equipment equipments = rentalService.findByEquipment(classname, monday);

        //then
        assertThat(equipments.getName()).isEqualTo(equipmentName);      //장비 이름이 "pmw-test" 나와야함
        assertThat(saveRental.getRentalCnt()).isEqualTo(inputCount);    // 빌린 갯수가 10개여야한다

    }

    @DisplayName("장비를 반납했으면 빌린 횟수가 0이 되어야함")
    @Test
    void returnRental() {
        //given
        Rental saveRental = rentalRepository.save(Rental.builder() //pmw 제품 3개 대여함
                .classTimeListId(getClassTimeList())
                .equipmentId(getEquipment()).rentalCnt(3)
                .build());

        RentalReturn request = RentalReturn.builder()
                .rentalId(saveRental.getId())
                .rentalCnt(3).build();// 사용자 요청으로 인해 3개 반납하였음

        //when
        rentalService.rentalReturn(request);

        //then
        Rental findRental = rentalRepository.findAll().get(0);
        assertThat(findRental.getRentalCnt()).isEqualTo(0);
    }

    private ClassTimeList getClassTimeList() {
        return classTimeRepository.findAll().get(0);
    }

    private Equipment getEquipment() {
        return equipmentRepository.findAll().get(0);
    }
}
