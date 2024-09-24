package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassPeriodRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
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
     * 1. 수업 시간에 요일이 없을 경우 에러가 발생
     * 2. 수업 시간이 저장이 되어야함
     */
    @DisplayName("요일저장 테스트 로직")
    @Test
    void save() throws Exception {
        //given
        ClassPeriod classPeriodMonday = ClassPeriod.builder()
                .dayOfWeek(DayOfWeekEnum.monday)
                .className("월요일에 관련된 수업")
                .oneTime(true).build();

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
}