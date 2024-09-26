package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.message.error.Message;
import com.equipment.school_equipment.repository.ClassPeriodRepository;
import com.equipment.school_equipment.request.admin.ClassPeriodPageCondition;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;

import static com.equipment.school_equipment.message.error.Message.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassPeriodService {
    private final ClassPeriodRepository classPeriodRepository;

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
        return classPeriodRepository.save(classTime);
    }

    /**
     * 수업 시간표 조회
     * @param week 요일
     * @return List<ClassPeriod>
     */
    public List<ClassPeriod> findByRentalWeek(String week) {
        DayOfWeekEnum dayOfWeekEnum = DayOfWeekEnum.getName(week);
        List<ClassPeriod> findByRental = classPeriodRepository.findByDayOfWeekEquals(dayOfWeekEnum);
        return findByRental;
    }

    /**
     * 수업 시간표 수정
     * @param request ClassTimeUpdate
     * @return ClassPeriod
     */
    @Transactional
    public ClassPeriod updateClassTime(ClassTimeUpdate request) {
        ClassPeriod findClassName = classPeriodRepository.findByClassName(request.oldClassname())
                .orElseThrow(() -> new EntityNotFoundException(CLASSPERIOD_FIND_CLASSROOM_ERROR));

        findClassName.setUpdate(request.newClassname(), request.dayOfWeekEnum(), request.oneTime(), request.twoTime(), request.threeTime(), request.fourTime(), request.fiveTime(), request.sixTime(), request.sevenTime(), request.eightTime(), request.nineTime(), request.tenTime());
        return findClassName;
    }

    /**
     * 수업 시간표 삭제
     * @param id ClassPeriod id
     */
    @Transactional
    public void delete(Long id) {
        classPeriodRepository.deleteById(id);
    }

    /**
     * 요일별 수업 시간표 조회
     * @param week 요일
     * @return  List<ClassPeriod>
     * @throws RuntimeException 요일이 없을 때
     */
    public List<ClassPeriod> findByDay(String week) throws RuntimeException {
        try{
            DayOfWeekEnum.valueOf(week); // 요일이 있는지 확인
        } catch (IllegalArgumentException e) { // 요일이 없을 경우
            throw new IllegalArgumentException(DayOfWeekEnum.DAYOFWEEK_ERROR);
        }
        return classPeriodRepository.findByDayOfWeekEquals(DayOfWeekEnum.valueOf(week));
    }

    /**
     * 수업 시간표 전체 조회
     * @return List<ClassPeriod>
     */
    public List<ClassPeriod> findAll() {
        return classPeriodRepository.findAll();
    }

    /**
     * 수업 시간표 페이징 조회
     * @param page ClassPeriodPageCondition
     * @return Page<ClassPeriod>
     */
    public Page<ClassPeriod> findAllPage(ClassPeriodPageCondition page) {
        return classPeriodRepository.classPeriodPage(page, PageRequest.of(page.getPage(), 10));
    }

    /**
     * 수업 시간표 조회
     * @param classnameId ClassPeriod id
     * @return ClassPeriod
     */
    public ClassPeriod findById(Long classnameId) {
        ClassPeriod classPeriod = classPeriodRepository.findById(classnameId)
                .orElseThrow(() -> new EntityNotFoundException(CLASSPERIOD_FIND_CLASSROOM_ERROR));

        return classPeriod;
    }
}
