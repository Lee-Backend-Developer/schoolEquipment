package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassTimeService {
    private final ClassTimeRepository classTimeRepository;

    @Transactional
    public Classtimes save(ClassTimeCreate request) throws RuntimeException {
        if(request.dayOfWeek() == null) throw new RuntimeException("요일이 선택되지 않음.");

        Classtimes classTime = Classtimes.builder()
                .className(request.className())
                .dayOfWeek(request.dayOfWeek())
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
    public Classtimes updateClassTime(ClassTimeUpdate request) {
        Classtimes findClassName = classTimeRepository.findByClassName(request.oldClassname()).orElseThrow(() -> new RuntimeException("접근에러"));

        findClassName.setUpdate(request.newClassname(), request.dayOfWeekEnum(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return findClassName;
    }

    @Transactional
    public void delete(Long id) {
        classTimeRepository.deleteById(id);
    }

    public List<Classtimes> findByDay(String week) throws RuntimeException {
        try{
            DayOfWeekEnum.valueOf(week); // 월요일
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("입력하신 요일은 없습니다.");
        }
        return classTimeRepository.findByDayOfWeekEquals(DayOfWeekEnum.valueOf(week));
    }

    public List<Classtimes> findAll() {
        return classTimeRepository.findAll();
    }

    public Classtimes findById(Long classnameId) {
        Classtimes classtimes = classTimeRepository.findById(classnameId).orElseThrow(() -> new RuntimeException("잘못된 접근입니다."));

        return classtimes;
    }
}
