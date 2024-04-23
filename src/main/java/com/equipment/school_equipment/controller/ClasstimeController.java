package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.service.ClassTimeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ClasstimeController {
    private final ClassTimeService classTimeService;

    @RequestMapping("/classtimes")
    public String classtimes(Model model) {
        model.addAttribute(DayOfWeekEnum.values());
        return "classTimeList";
    }
}
