package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClassTimeServiceTest {
    @Autowired
    ClassTimeRepository classTimeRepository;
    @Autowired
    ClassTimeService classTimeService;

    @DisplayName("수업등록")
    @Test
    void classTimeCreate_O(){
        //given
        ClassTimeCreate request = ClassTimeCreate
                .builder()
                .className("영상실습")
                .className("영상촬영실습")
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build();

        //when
        ClassTime saveClassTime = classTimeService.save(request);

        //then
        Assertions.assertThat(classTimeRepository.findById(saveClassTime.getId()).isEmpty());
    }
}