package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.service.NotificationProductService;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession httpSession;

    @GetMapping("/")
    public String MainHomePage(Model model) {
        model.addAttribute("findList", notificationProductService.findList());
        httpSession.getAttributeNames().asIterator().forEachRemaining(a -> log.info("session => {}", a));
        log.info("context => {}", httpSession.getAttribute("SPRING_SECURITY_CONTEXT"));

        return "mainHome";
    }

    @GetMapping("500")
    public String serverError() {
        return "error/5xx";
    }
}
