package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.Classtimetable;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassTimeService {
    private static final Logger log = LoggerFactory.getLogger(ClassTimeService.class);
    private final ClassTimeRepository classTimeRepository;

    @Transactional
    public Classtimetable save(ClassTimeCreate request) throws RuntimeException {
        if(request.dayOfWeek() == null) throw new RuntimeException("요일이 선택되지 않음.");

        Classtimetable classTime = Classtimetable.builder()
                .className(request.className())
                .oneTime(request.oneTime())
                .twoTime(request.twoTime())
                .threeTime(request.threeTime())
                .fourTime(request.fourTime())
                .fiveTime(request.fiveTime())
                .sixTime(request.sixTime())
                .sevenTime(request.sevenTime())
                .eightTime(request.eightTime())
                .nineTime(request.nineTime())
                .tenTime(request.tenTime())
                .build();
        return classTimeRepository.save(classTime);
    }

    @Transactional
    public Classtimetable updateClassTime(ClassTimeUpdate request) {
        Classtimetable findClassName = classTimeRepository.findByClassName(request.oldClassname()).get();

        findClassName.setUpdate(request.newClassname(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return findClassName;
    }

    @Transactional
    public void delete(Long id) {
        classTimeRepository.deleteById(id);
    }

    public List<Classtimetable> findByDay(String week) throws RuntimeException {
        try{
            DayOfWeekEnum.valueOf(week); // 월요일
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("입력하신 요일은 없습니다.");
        }
        return classTimeRepository.findByDayOfWeekEquals(DayOfWeekEnum.valueOf(week));
    }
}
