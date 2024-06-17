package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.service.NotificationContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationContentService notificationContentService;

    @GetMapping
    public String getNotification(Model model){
        model.addAttribute("content", notificationContentService.finds().getContent());
        return "notification";
    }

}
