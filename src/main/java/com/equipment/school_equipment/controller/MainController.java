package com.equipment.school_equipment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/test")
    public String MainHomePage() {
        log.info("MainHomePage");
        return "mainHome";
    }
}
