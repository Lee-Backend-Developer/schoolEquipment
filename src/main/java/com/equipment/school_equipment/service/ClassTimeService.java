package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.ClassTime;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassTimeService {
    private final ClassTimeRepository classTimeRepository;


    public ClassTime save(ClassTimeCreate request) {
        ClassTime classTime = new ClassTime(request.className(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return classTimeRepository.save(classTime);
    }
}
