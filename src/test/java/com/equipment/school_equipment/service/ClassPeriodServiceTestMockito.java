package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassPeriod;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassPeriodServiceTestMockito {
    @Mock
    ClassTimeRepository classTimeRepository;

    @InjectMocks
    ClassPeriodService classPeriodService;

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
        when(classPeriodService.save(request))
                .thenReturn(ClassPeriod.builder()
                .className("영상실습")
                .dayOfWeek(DayOfWeekEnum.monday)
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build());

        //then
        ClassPeriod findClassTime = classPeriodService.save(request);
        assertThat(findClassTime).isNotNull();
    }

    @DisplayName("수업 아이디를 검색했을때 수업 도메인이 가져와야된다.")
    @Test
    void findById_O() throws Exception {
        //given 준비 과정
        ClassPeriod saveClassPeriod = ClassPeriod.builder()
                .id(1L)
                .className("국어")
                .dayOfWeek(DayOfWeekEnum.monday)
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build();

        //when 실행
        when(classTimeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(saveClassPeriod));

        //then 검증
        ClassPeriod findByClassperiod = classPeriodService.findById(1L);
        assertThat(findByClassperiod.getId()).isEqualTo(1L);
    }

    @DisplayName("요일별로 시간표를 가지고 온다")
    @Test
    void findOfDays() {
        //given
        ClassPeriod[] classTimes = new ClassPeriod[5];

        classTimes[0] = ClassPeriod.builder().className("영상촬영실습").dayOfWeek(DayOfWeekEnum.monday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[1] = ClassPeriod.builder().className("방송기술계열").dayOfWeek(DayOfWeekEnum.tuesday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[2] = ClassPeriod.builder().className("촬영이론및실습").dayOfWeek(DayOfWeekEnum.wednesday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[3] = ClassPeriod.builder().className("숏폼콘텐츠제작/촬영").dayOfWeek(DayOfWeekEnum.thursday).twoTime(true).threeTime(true).fourTime(true).build();
        classTimes[4] = ClassPeriod.builder().className("영상미디어디자인연구").dayOfWeek(DayOfWeekEnum.friday).twoTime(true).threeTime(true).fourTime(true).build();

        //when
        when(classPeriodService.findByDay(DayOfWeekEnum.monday.name()))
                .thenReturn(Arrays.stream(classTimes).toList());
        when(classPeriodService.findByDay(DayOfWeekEnum.tuesday.name()))
                .thenReturn(Arrays.stream(classTimes).toList());
        when(classPeriodService.findByDay(DayOfWeekEnum.wednesday.name()))
                .thenReturn(Arrays.stream(classTimes).toList());
        when(classPeriodService.findByDay(DayOfWeekEnum.thursday.name()))
                .thenReturn(Arrays.stream(classTimes).toList());
        when(classPeriodService.findByDay(DayOfWeekEnum.friday.name()))
                .thenReturn(Arrays.stream(classTimes).toList());

        ClassPeriod monday = classPeriodService.findByDay(DayOfWeekEnum.monday.name()).get(0);
        ClassPeriod tuesday = classPeriodService.findByDay(DayOfWeekEnum.tuesday.name()).get(1);
        ClassPeriod wednesday = classPeriodService.findByDay(DayOfWeekEnum.wednesday.name()).get(2);
        ClassPeriod thursday = classPeriodService.findByDay(DayOfWeekEnum.thursday.name()).get(3);
        ClassPeriod friday = classPeriodService.findByDay(DayOfWeekEnum.friday.name()).get(4);

        //then
        assertThat(monday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.monday);
        assertThat(tuesday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.tuesday);
        assertThat(wednesday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.wednesday);
        assertThat(thursday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.thursday);
        assertThat(friday.getDayOfWeek()).isEqualTo(DayOfWeekEnum.friday);
        assertThat(DayOfWeekEnum.monday.name()).isNotNull();
    }

    @DisplayName("영상실습수업이 영상이론 수업으로 변경이 되어야 한다")
    @Test
    void classTimeUpdate_O() {
        //given
        ClassPeriod oldClassTime = ClassPeriod.builder().id(1L).className("영상실습").twoTime(true).threeTime(true).fourTime(true).build();
        classTimeRepository.save(oldClassTime);

        //when
        when(classTimeRepository.findByClassName(oldClassTime.getClassName()))
                .thenReturn(Optional.of(oldClassTime));
        assertThat(classTimeRepository.findByClassName(oldClassTime.getClassName()).get().getClassName()).isEqualTo("영상실습");


        String newClassName = "영상이론";
        ClassTimeUpdate request = ClassTimeUpdate.builder().updateClassname("영상실습", newClassName).oneTime(true).build();
        classPeriodService.updateClassTime(request);

        when(classTimeRepository.findById(anyLong())).thenReturn(Optional.of(oldClassTime));

        //then
        ClassPeriod findClassTime = classTimeRepository.findById(oldClassTime.getId()).get();
        assertThat(findClassTime.getClassName()).isEqualTo(newClassName);
    }

    @DisplayName("수업 이름을 검색하여 아이디를 가지고 삭제가 되어야한다")
    @Test
    void classTimeDelete_O() {
        //given
        ClassPeriod saveClassTime = ClassPeriod.builder().className("영상실습").twoTime(true).threeTime(true).fourTime(true).build();
        classTimeRepository.save(saveClassTime);

        //when
        classPeriodService.delete(saveClassTime.getId());

        //then
        assertThat(classTimeRepository.findById(saveClassTime.getId())).isEmpty();
    }
}