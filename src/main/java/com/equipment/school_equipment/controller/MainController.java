package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.service.NotificationProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final NotificationProductService notificationProductService;

    @GetMapping("/")
    public String MainHomePage(Model model) {
        model.addAttribute("findList", notificationProductService.findList());
        return "mainHome";
    }
}
