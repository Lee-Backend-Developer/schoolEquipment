package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Category category = Category.builder().categoryName("카메라").build();
        categoryRepository.save(category);

        Equipment equipment = Equipment.builder().name("pmw").count(10).build();
        equipmentRepository.save(equipment);
        equipment.addCategory(category);

        classTimeRepository.save(Classtimes.builder()
                .className("영상촬영실습").dayOfWeek(DayOfWeekEnum.monday)
                .twoTime(true).threeTime(true).fourTime(true).build());
    }

    @AfterEach
    void end() {
        equipmentRepository.deleteAll();
        categoryRepository.deleteAll();
        classTimeRepository.deleteAll();
        rentalRepository.deleteAll();
    }

    @DisplayName("장비를 대여가 되고 장비도 대어한 만큼 줄어들어야함")
    @Test
    void createRental() {
        //given
        RentalCreate request = RentalCreate.builder()
                .className("영상촬영실습")
                .dayOfWeekEnum(DayOfWeekEnum.monday)
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

    @DisplayName("장비 4개 대여하고 6개 남아야한다.")
    @Test
    void equipmentLeft(){
        //given
        Rental rental = Rental.builder().classtimesId(getClassTimeList())
                .equipmentId(getEquipment())
                .rentalCnt(2)
                .rentalChk(true)// 10 - 2
                .build();

        Rental rental2 = Rental.builder().classtimesId(getClassTimeList())
                .equipmentId(getEquipment())
                .rentalCnt(2) // 10 - 2
                .rentalChk(true)
                .build();

        rentalRepository.save(rental);
        rentalRepository.save(rental2);

        String equipmentName = rental.getEquipmentId().getName();

        //when
        int leftCnt = rentalService.findByEquipmentCnt(equipmentName); //남은 값

        //then
        assertThat(leftCnt).isEqualTo(6);
    }

    @DisplayName("수업명과 요일을 입력받아 장비가 몇 개 들어가는지 조회가 되어야함")
    @Test
    void classTimeRental() {
        //given
        Category category = new Category("카메라");
        categoryRepository.save(category);

        String classname = "배우연기연출";
        String monday = DayOfWeekEnum.monday.name();
        String equipmentName = "pmw-test";
        int maxCount = 20;
        int inputCount = 10;

        Classtimes saveClasstime = classTimeRepository.save(Classtimes.builder().className(classname)
                .dayOfWeek(DayOfWeekEnum.valueOf(monday))
                .twoTime(true).build());


        Equipment saveEquipment = equipmentRepository.save(Equipment.builder().name(equipmentName).count(maxCount).build());
        saveEquipment.addCategory(category);


        //when
        Rental saveRental = Rental.builder()
                .classtimesId(saveClasstime)
                .equipmentId(saveEquipment)
                .rentalCnt(inputCount)
                .build();
        rentalRepository.save(saveRental);

        Equipment equipments = rentalService.findByEquipment(classname, monday);
        equipments.addCategory(category);

        //then
        assertThat(equipments.getName()).isEqualTo(equipmentName);      //장비 이름이 "pmw-test" 나와야함
        assertThat(saveRental.getRentalCnt()).isEqualTo(inputCount);    // 빌린 갯수가 10개여야한다

    }


    @DisplayName("수업명과 요일 입력받아 대여된 장비들이 나와야한다")
    @Test
    void findByClassNameIdAndDayOfWeek() {
        //given
        String dayOfWeek = DayOfWeekEnum.monday.name();
        String classNameId = Long.toString(getClassTimeList().getId());
        Rental rental = Rental.builder().equipmentId(getEquipment()).classtimesId(getClassTimeList()).rentalCnt(3).build();
        rentalRepository.save(rental);


        //when
        List<Equipment> equipment = rentalService.findByClassnameIdAndDayOfWeek(classNameId, dayOfWeek);

        //then
        assertThat(equipment.size()).isEqualTo(1);
        assertThat(equipment).contains(rental.getEquipmentId());
        assertThat(rental.getRentalCnt()).isEqualTo(3);

    }

    private Classtimes getClassTimeList() {
        return classTimeRepository.findAll().get(0);
    }

    private Equipment getEquipment() {
        return equipmentRepository.findAll().get(0);
    }
}
