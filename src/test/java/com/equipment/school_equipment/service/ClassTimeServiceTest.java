package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Classes;
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
                .dayOfWeek(DayOfWeekEnum.monday)
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build();

        //when
        Classes saveClassTime = classTimeService.save(request);

        //then
        Classes findClassTime = classTimeRepository.findById(saveClassTime.getClassesId()).get();
        Assertions.assertThat(findClassTime).isNotNull();
    }

    @DisplayName("수업 아이디를 검색했을때 수업 도메인이 가져와야된다.")
    @Test
    void findById_O() throws Exception {
        //given 준비 과정
        Classes saveClasses = Classes.builder()
                .className("국어")
                .dayOfWeek(DayOfWeekEnum.monday)
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build();

        classTimeRepository.save(saveClasses);

        //when 실행
        Classes findClasses = classTimeService.findById(saveClasses.getClassesId());

        //then 검증
        Assertions.assertThat(findClasses.getClassName()).isEqualTo(saveClasses.getClassName());
    }

    @DisplayName("요일별로 시간표를 가지고 온다")
    @Test
    void findOfDays() {
        //given
        Classes[] classTimes = new Classes[5];

        classTimes[0] = Classes.builder().className("영상촬영실습").dayOfWeek(DayOfWeekEnum.monday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[1] = Classes.builder().className("방송기술계열").dayOfWeek(DayOfWeekEnum.tuesday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[2] = Classes.builder().className("촬영이론및실습").dayOfWeek(DayOfWeekEnum.wednesday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[3] = Classes.builder().className("숏폼콘텐츠제작/촬영").dayOfWeek(DayOfWeekEnum.thursday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[4] = Classes.builder().className("영상미디어디자인연구").dayOfWeek(DayOfWeekEnum.friday).twoTime(true).threeTime(true).fourTime(true).build();

        classTimeRepository.saveAll(Arrays.asList(classTimes));

        //when
        Classes monday = classTimeService.findByDay(DayOfWeekEnum.monday.name()).get(0);
        Classes tuesday = classTimeService.findByDay(DayOfWeekEnum.tuesday.name()).get(0);
        Classes wednesday = classTimeService.findByDay(DayOfWeekEnum.wednesday.name()).get(0);
        Classes thursday = classTimeService.findByDay(DayOfWeekEnum.thursday.name()).get(0);
        Classes friday = classTimeService.findByDay(DayOfWeekEnum.friday.name()).get(0);

        //then
        Assertions.assertThat(monday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.monday);
        Assertions.assertThat(tuesday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.tuesday);
        Assertions.assertThat(wednesday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.wednesday);
        Assertions.assertThat(thursday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.thursday);
        Assertions.assertThat(friday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.friday);
    }

    @DisplayName("영상실습수업이 영상이론 수업으로 변경이 되어야 한다")
    @Test
    void classTimeUpdate_O() {
        //given
        Classes oldClassTime = Classes.builder().className("영상실습").twoTime(true).threeTime(true).fourTime(true).build();
        classTimeRepository.save(oldClassTime);

        //when
        String newClassName = "영상이론";

        ClassTimeUpdate request = ClassTimeUpdate.builder().updateClassname("영상실습", newClassName).oneTime(true).build();
        classTimeService.updateClassTime(request);
        //then
        Classes findClassTime = classTimeRepository.findById(oldClassTime.getClassesId()).get();
        Assertions.assertThat(findClassTime.getClassName()).isEqualTo(newClassName);
    }

    @DisplayName("수업 이름을 검색하여 아이디를 가지고 삭제가 되어야한다")
    @Test
    void classTimeDelete_O() {
        //given
        Classes saveClassTime = Classes.builder().className("영상실습").twoTime(true).threeTime(true).fourTime(true).build();
        classTimeRepository.save(saveClassTime);

        //when
        classTimeService.delete(saveClassTime.getClassesId());

        //then
        Assertions.assertThat(classTimeRepository.findById(saveClassTime.getClassesId())).isEmpty();
    }
}