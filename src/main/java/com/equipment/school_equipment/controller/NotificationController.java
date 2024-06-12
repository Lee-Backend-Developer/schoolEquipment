package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
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

    @GetMapping("product")
    public String getProduct(Model model){
        NotificationRequest requestForm = NotificationRequest.builder().build();
        model.addAttribute("requestForm", requestForm);
        return "admin/notification/edit";
    }
}
