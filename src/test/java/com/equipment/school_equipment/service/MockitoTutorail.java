package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassPeriod;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockitoTutorail {

    @InjectMocks //classPeriodService 생성자에 classTimeRepository 주입
    ClassPeriodService classPeriodService;

    @Mock // 가짜 객체 생성
    ClassTimeRepository classTimeRepository;

    private final ClassPeriod classPeriod = ClassPeriod.builder().id(1L).className("월요일").build();

    @Mock
    List<String> mockList;      //mockList 생성

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockList.add("one");                         // mockList에 "one" 문자열 추가
        verify(mockList).add("one");         // .add("one") 메소드 잘 실행이 됐는지 확인
        assertEquals(0, mockList.size());   // size가 0인지확인

        when(mockList.size()).thenReturn(100); // when 동작 -> size() 함수에 100를 넣어줌
        assertEquals(100, mockList.size());
    }

    @Test
    void test() throws Exception {
        when(classTimeRepository.findById(1L)).thenReturn(Optional.of(classPeriod));
        System.out.println(classTimeRepository.findById(1L).get().getClassName());
    }

    @Test
    void serviceTest() throws Exception {
        when(classTimeRepository.findById(1L)).thenReturn(Optional.of(classPeriod));
//        when(classPeriodService.findById(1L)).thenReturn(classPeriod);
        assertEquals(1L, classPeriodService.findById(1L).getId());

    }
}