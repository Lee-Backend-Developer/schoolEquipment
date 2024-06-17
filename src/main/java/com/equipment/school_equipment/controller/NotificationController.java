package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
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
@RequestMapping("/admin/notification")
public class NotificationController {
    private final NotificationContentService notificationContentService;

    @GetMapping("product")
    public String getProduct(Model model){
        Long id = notificationContentService.finds().getId();
        NotificationRequest requestForm = NotificationRequest.builder().id(id).build();
        model.addAttribute("requestForm", requestForm);
        return "admin/notification/edit";
    }
}
