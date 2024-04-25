package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.Classtimetable;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.service.ClassTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute(classtimetableList);
        return "day";
    }
}
