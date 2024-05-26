package com.equipment.school_equipment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String MainHomePage() {
        return "mainHome";
    }

    @GetMapping("test")
    public String test() {
        return "fragments2/equipment";
    }
}
