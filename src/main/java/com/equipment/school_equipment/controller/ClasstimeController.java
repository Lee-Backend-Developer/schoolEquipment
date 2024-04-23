package com.equipment.school_equipment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ClasstimeController {

    @RequestMapping("/classtimes")
    public String classtimes() {
        return "classTimeList";
    }
}
