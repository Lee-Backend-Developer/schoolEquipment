package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.response.thymeleaf.ClasstimeResponse;
import com.equipment.school_equipment.service.ClassPeriodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/class-schedule")
public class ClassPeriodController {
    private final ClassPeriodService classPeriodService;

    @GetMapping
    public String viewWeek(Model model) {
        model.addAttribute(DayOfWeekEnum.values());
        return "classTimeList";
    }

    @GetMapping("/{schedule}")
    public String findByWeekdayClassPeriod(@PathVariable String schedule, Model model){
        List<ClassPeriod> classPeriodList = classPeriodService.findByDay(schedule);
        List<ClasstimeResponse> responsesList = new ArrayList<>();

        classPeriodList.iterator().forEachRemaining(
                classperiod -> {
                    List<Boolean> times = getTimes(classperiod); // 교시 가져오기
                    responsesList.add(ClasstimeResponse.builder()
                            .id(classperiod.getId())
                            .classname(classperiod.getClassName())
                            .dayOfWeekEnum(classperiod.getDayOfWeek())
                            .times(times)
                            .build());
                }
        );
        model.addAttribute(responsesList);
        return "day";
    }


    private static List<Boolean> getTimes(ClassPeriod classTimeList) {
        List<Boolean> times = new ArrayList<>();
        times.add(classTimeList.isOneTime());
        times.add(classTimeList.isTwoTime());
        times.add(classTimeList.isThreeTime());
        times.add(classTimeList.isFourTime());
        times.add(classTimeList.isFiveTime());
        times.add(classTimeList.isSixTime());
        times.add(classTimeList.isSevenTime());
        times.add(classTimeList.isEightTime());
        times.add(classTimeList.isNineTime());
        times.add(classTimeList.isTenTime());
        return times;
    }
}
