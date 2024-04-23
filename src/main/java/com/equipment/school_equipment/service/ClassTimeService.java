package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassTimeService {
    private final ClassTimeRepository classTimeRepository;

    @Transactional
    public ClassTime save(ClassTimeCreate request) throws RuntimeException {
        if(request.dayOfWeek() == null) throw new RuntimeException("요일이 선택되지 않음.");

        ClassTime classTime = ClassTime.builder()
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
    public ClassTime updateClassTime(ClassTimeUpdate request) {
        ClassTime findClassName = classTimeRepository.findByClassName(request.oldClassname()).get();

        findClassName.setUpdate(request.newClassname(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return findClassName;
    }

    @Transactional
    public void delete(Long id) {
        classTimeRepository.deleteById(id);
    }
}
