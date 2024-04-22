package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassTimeService {
    private final ClassTimeRepository classTimeRepository;


    @Transactional
    public ClassTime save(ClassTimeCreate request) {
        ClassTime classTime = new ClassTime(request.className(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return classTimeRepository.save(classTime);
    }

    @Transactional
    public ClassTime updateClassTime(ClassTimeUpdate request) {
        ClassTime findClassName = classTimeRepository.findByClassName(request.oldClassname()).get();

        findClassName.setUpdate(request.newClassname(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return findClassName;
    }
}
