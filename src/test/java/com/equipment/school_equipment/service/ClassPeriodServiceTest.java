package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassPeriodRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ClassPeriodServiceTest {

    @Mock
    public ClassPeriodRepository classPeriodRepository;

    @InjectMocks
    public ClassPeriodService classPeriodService;

    /**
     * <수업 시간표 저장>
     * 1. 수업 시간에 요일이 없을 경우 에러가 발생
     * 2. 수업 시간이 저장이 되어야함
     */
    @DisplayName("요일저장이 되어야한다.")
    @Test
    void save_throw() throws Exception {
        //given
        ClassPeriod classPeriodMonday = getMonday();

        given(classPeriodRepository.save(any(ClassPeriod.class)))
                .willReturn(classPeriodMonday);

        //when
        ClassTimeCreate classPeriodMondayRequest = ClassTimeCreate.builder()
                .dayOfWeek(DayOfWeekEnum.monday)
                .className("월요일에 관련된 수업")
                .oneTime(true).build();

        ClassPeriod saveClassPeriod = classPeriodService.save(classPeriodMondayRequest);

        //then

        /* 요일이 들어있지 않다면 오류가 발생해야함 */
        ClassTimeCreate createDto = ClassTimeCreate.builder()
                .className("과학책")
                .oneTime(true)
                .twoTime(true)
                .build();
        assertThrows(RuntimeException.class, () -> {
            classPeriodService.save(createDto);
        });

        /* 수업이 저장이 되어야한다. */
        assertNotNull(saveClassPeriod);

    }


    /**
     * <수업 시간표 수정>
     * 1. DB에 조회했을 때 수업명이 없을 경우 예외처리가 되어야한다.
     * 2. 수업 이름이 변경이 되어야한다.
     */
    @DisplayName("수업 시간표가 수정이 되어야한다.")
    @Test
    void edit_O() throws Exception {
        //given 준비 과정
        ClassPeriod classPeriodMonday = getMonday();

        given(classPeriodRepository.findByClassName("월요일에 관련된 수업")) // 저장소 조회시 가짜객체 리턴함
                .willReturn(Optional.of(classPeriodMonday));

        //when 실행
        ClassTimeUpdate classTimeTuesdayRequest = ClassTimeUpdate.builder()
                .updateClassname("월요일에 관련된 수업", "화요일에 관련된 수업")
                .dayOfWeek(DayOfWeekEnum.tuesday)
                .build();

        ClassPeriod classPeriodTuesday = classPeriodService.updateClassTime(classTimeTuesdayRequest);

        //then 검증



        /* 2. 수업명이 "화요일에 관련된 수업" 변경이*/
        assertEquals(classPeriodTuesday.getClassName(), "화요일에 관련된 수업");
    }

    @DisplayName("")
    @Test
    void edit_throw() throws Exception {
        //given 준비 과정
        ClassTimeUpdate classPeriodDto = ClassTimeUpdate.builder()
                .updateClassname("변경 전 수업", "변경 후 수업")
                .build();
        //when 실행
        /* 1. 수업 이름이 존재하지 않을 경우 에러가 발생 */
        assertThrows(RuntimeException.class, () -> {
            classPeriodService.updateClassTime(classPeriodDto);
        });

    }

    private static ClassPeriod getMonday() {
        ClassPeriod classPeriodMonday = ClassPeriod.builder()
                .dayOfWeek(DayOfWeekEnum.monday)
                .className("월요일에 관련된 수업")
                .oneTime(true).build();
        return classPeriodMonday;
    }
}