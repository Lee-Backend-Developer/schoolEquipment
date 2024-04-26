package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Classtimetable;
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
@RequestMapping("/classtimes")
public class ClasstimeController {
    private final ClassTimeService classTimeService;

    @RequestMapping
    public String classtimes(Model model) {
        model.addAttribute(DayOfWeekEnum.values());
        return "classTimeList";
    }

    @RequestMapping("/{dayofweek}")
    public String dayofweek(@PathVariable String dayofweek, Model model){
        List<Classtimetable> classtimetableList = classTimeService.findByDay(dayofweek);
        List<ClasstimeResponse> responsesList = new ArrayList<>();

        classtimetableList.iterator().forEachRemaining(
                classtimetable -> {
                    List<Boolean> times = getTimes(classtimetable); // 교시 가져오기
                    responsesList.add(ClasstimeResponse.builder()
                            .id(classtimetable.getId())
                            .classname(classtimetable.getClassName())
                            .dayOfWeekEnum(classtimetable.getDayOfWeek())
                            .times(times)
                            .build());
                }
        );
        model.addAttribute(responsesList);
        return "day";
    }

    private static List<Boolean> getTimes(Classtimetable classtimetable) {
        List<Boolean> times = new ArrayList<>();
        times.add(classtimetable.isOneTime());
        times.add(classtimetable.isTwoTime());
        times.add(classtimetable.isThreeTime());
        times.add(classtimetable.isFourTime());
        times.add(classtimetable.isFiveTime());
        times.add(classtimetable.isSixTime());
        times.add(classtimetable.isSevenTime());
        times.add(classtimetable.isEightTime());
        times.add(classtimetable.isNineTime());
        times.add(classtimetable.isTenTime());
        return times;
    }
}
