package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    void classTimeCreate_O() {
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
        ClassTime findClassTime = classTimeRepository.findById(saveClassTime.getId()).get();
        Assertions.assertThat(findClassTime).isNotNull();
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