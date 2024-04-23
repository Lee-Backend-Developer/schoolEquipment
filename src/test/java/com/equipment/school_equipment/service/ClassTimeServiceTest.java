package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClassTimeServiceTest {
    @Autowired
    ClassTimeRepository classTimeRepository;
    @Autowired
    ClassTimeService classTimeService;

    @AfterEach
    void end() {
        classTimeRepository.deleteAll();
    }

    @DisplayName("수업등록")
    @Test
    void classTimeCreate_O() {
        //given
        ClassTimeCreate request = ClassTimeCreate
                .builder()
                .className("영상실습")
                .dayOfWeek(DayOfWeekEnum.Monday)
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build();

        //when
        ClassTime saveClassTime = classTimeService.save(request);

        //then
        ClassTime findClassTime = classTimeRepository.findById(saveClassTime.getId()).get();
        Assertions.assertThat(findClassTime).isNotNull();
    }

    @DisplayName("요일별로 시간표를 가지고 온다")
    @Test
    void findOfDays() {
        //given
        ClassTime[] classTimes = new ClassTime[5];

        classTimes[0] = ClassTime.builder().className("영상촬영실습").dayOfWeek(DayOfWeekEnum.Monday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[1] = ClassTime.builder().className("방송기술계열").dayOfWeek(DayOfWeekEnum.Tuesday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[2] = ClassTime.builder().className("촬영이론및실습").dayOfWeek(DayOfWeekEnum.Wednesday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[3] = ClassTime.builder().className("숏폼콘텐츠제작/촬영").dayOfWeek(DayOfWeekEnum.Thursday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[4] = ClassTime.builder().className("영상미디어디자인연구").dayOfWeek(DayOfWeekEnum.Friday).twoTime(true).threeTime(true).fourTime(true).build();

        classTimeRepository.saveAll(Arrays.asList(classTimes));

        //when
        ClassTime monday = classTimeService.findByDay(DayOfWeekEnum.Monday.name()).get(0);
        ClassTime tuesday = classTimeService.findByDay(DayOfWeekEnum.Tuesday.name()).get(0);
        ClassTime wednesday = classTimeService.findByDay(DayOfWeekEnum.Wednesday.name()).get(0);
        ClassTime thursday = classTimeService.findByDay(DayOfWeekEnum.Thursday.name()).get(0);
        ClassTime friday = classTimeService.findByDay(DayOfWeekEnum.Friday.name()).get(0);

        //then
        Assertions.assertThat(monday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.Monday);
        Assertions.assertThat(tuesday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.Tuesday);
        Assertions.assertThat(wednesday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.Wednesday);
        Assertions.assertThat(thursday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.Thursday);
        Assertions.assertThat(friday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.Friday);
    }

    @DisplayName("영상실습수업이 영상이론 수업으로 변경이 되어야 한다")
    @Test
    void classTimeUpdate_O() {
        //given
        ClassTime oldClassTime = ClassTime.builder().className("영상실습").twoTime(true).threeTime(true).fourTime(true).build();
        classTimeRepository.save(oldClassTime);

        //when
        String newClassName = "영상이론";

        ClassTimeUpdate request = ClassTimeUpdate.builder().updateClassname("영상실습", newClassName).oneTime(true).build();
        classTimeService.updateClassTime(request);
        //then
        ClassTime findClassTime = classTimeRepository.findById(oldClassTime.getId()).get();
        Assertions.assertThat(findClassTime.getClassName()).isEqualTo(newClassName);
    }

    @DisplayName("수업 이름을 검색하여 아이디를 가지고 삭제가 되어야한다")
    @Test
    void classTimeDelete_O() {
        //given
        ClassTime saveClassTime = ClassTime.builder().className("영상실습").twoTime(true).threeTime(true).fourTime(true).build();
        classTimeRepository.save(saveClassTime);

        //when
        classTimeService.delete(saveClassTime.getId());

        //then
        Assertions.assertThat(classTimeRepository.findById(saveClassTime.getId())).isEmpty();
    }
}