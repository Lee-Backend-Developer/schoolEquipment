package com.equipment.school_equipment.repository;


import com.equipment.school_equipment.domain.ClassTimeList;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ClassTimeRepositoryTest {

    @Autowired
    private ClassTimeRepository classTimeRepository;

    @Test
    @DisplayName("수업이 저장이 되어야한다")
    public void create() {
        // given 무엇을 주어졌을때

        ClassTimeList classTime = ClassTimeList.builder()
                .className("영상촬영실습")
                .dayOfWeek(DayOfWeekEnum.Monday)
                .twoTime(true)
                .threeTime(true)
                .fourTime(true)
                .build();

        // when 이러한 행동을 할 때
        ClassTimeList saveClassTime = classTimeRepository.save(classTime);

        // then 결과는 이렇게 되야됨
        ClassTimeList findByClassTime = classTimeRepository.findById(saveClassTime.getId()).get();
        assertThat("영상촬영실습").isEqualTo(findByClassTime.getClassName());
    }
}
