package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.request.admin.ClassPeriodPageCondition;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassPeriodService {
    private final ClassTimeRepository classTimeRepository;

    /**
     * 수업 시간표 저장
     * @param request ClassTimeCreate
     * @return ClassPeriod
     * @throws RuntimeException 요일이 선택되지 않았을 때
     */
    @Transactional
    public ClassPeriod save(ClassTimeCreate request) throws RuntimeException {
        if(request.dayOfWeek() == null) throw new RuntimeException("요일이 선택되지 않음.");

        ClassPeriod classTime = ClassPeriod.builder()
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

    /**
     * 수업 시간표 조회
     * @param week 요일
     * @return List<ClassPeriod>
     */
    public List<ClassPeriod> findByRentalWeek(String week) {
        DayOfWeekEnum dayOfWeekEnum = DayOfWeekEnum.getName(week);
        List<ClassPeriod> findByRental = classTimeRepository.findByDayOfWeekEquals(dayOfWeekEnum);
        return findByRental;
    }

    /**
     * 수업 시간표 수정
     * @param request ClassTimeUpdate
     * @return ClassPeriod
     */
    @Transactional
    public ClassPeriod updateClassTime(ClassTimeUpdate request) {
        ClassPeriod findClassName = classTimeRepository.findByClassName(request.oldClassname()).orElseThrow(() -> new RuntimeException("접근에러"));

        findClassName.setUpdate(request.newClassname(), request.dayOfWeekEnum(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return findClassName;
    }

    /**
     * 수업 시간표 삭제
     * @param id ClassPeriod id
     */
    @Transactional
    public void delete(Long id) {
        classTimeRepository.deleteById(id);
    }

    /**
     * 요일별 수업 시간표 조회
     * @param week 요일
     * @return  List<ClassPeriod>
     * @throws RuntimeException 요일이 없을 때
     */
    public List<ClassPeriod> findByDay(String week) throws RuntimeException {
        try{
            DayOfWeekEnum.valueOf(week); // 월요일
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("입력하신 요일은 없습니다.");
        }
        return classTimeRepository.findByDayOfWeekEquals(DayOfWeekEnum.valueOf(week));
    }

    /**
     * 수업 시간표 전체 조회
     * @return List<ClassPeriod>
     */
    public List<ClassPeriod> findAll() {
        return classTimeRepository.findAll();
    }

    /**
     * 수업 시간표 페이징 조회
     * @param page ClassPeriodPageCondition
     * @return Page<ClassPeriod>
     */
    public Page<ClassPeriod> findAllPage(ClassPeriodPageCondition page) {
        return classTimeRepository.classPeriodPage(page, PageRequest.of(page.getPage(), 10));
    }

    /**
     * 수업 시간표 조회
     * @param classnameId ClassPeriod id
     * @return ClassPeriod
     */
    public ClassPeriod findById(Long classnameId) {
        ClassPeriod classPeriod = classTimeRepository.findById(classnameId).orElseThrow(() -> new RuntimeException("잘못된 접근입니다."));

        return classPeriod;
    }
}
