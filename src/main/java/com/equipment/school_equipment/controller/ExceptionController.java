package com.equipment.school_equipment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("error")
public class ExceptionController {

    @GetMapping("permission")
    public String getPermission() {
        return "exception/4xx";
    }

}
