package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Classes;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.response.thymeleaf.ClasstimeResponse;
import com.equipment.school_equipment.service.ClassTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/classes")
public class ClassesController {
    private final ClassTimeService classTimeService;

    @RequestMapping
    public String classtimes(Model model) {
        model.addAttribute(DayOfWeekEnum.values());
        return "classTimeList";
    }

    @RequestMapping("/{dayofweek}")
    public String dayofweek(@PathVariable String dayofweek, Model model){
        List<Classes> classtimes = classTimeService.findByDay(dayofweek);
        List<ClasstimeResponse> responsesList = new ArrayList<>();

        classtimes.iterator().forEachRemaining(
                classtimetable -> {
                    List<Boolean> times = getTimes(classtimetable); // 교시 가져오기
                    responsesList.add(ClasstimeResponse.builder()
                            .id(classtimetable.getClassesId())
                            .classname(classtimetable.getClassName())
                            .dayOfWeekEnum(classtimetable.getDayOfWeek())
                            .times(times)
                            .build());
                }
        );
        model.addAttribute(responsesList);
        return "day";
    }


    private static List<Boolean> getTimes(Classes classTimeList) {
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
