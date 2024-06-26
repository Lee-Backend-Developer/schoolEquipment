package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.domain.NotificationContent;
import com.equipment.school_equipment.repository.NotificationContentRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import com.equipment.school_equipment.service.NotificationContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/notification/content")
public class NotificationContentApiController {
    private final NotificationContentService notificationContentService;

    @GetMapping
    public NotificationContent contentFind() {
        NotificationContent finds = notificationContentService.finds();
        return finds;
    }

    @PostMapping
    public ResponseEntity contentSave(NotificationRequest notificationRequest) {
        notificationContentService.save(notificationRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ModelAndView contentEdit(NotificationRequest notificationRequest) {
        ModelAndView modelAndView = new ModelAndView();
        notificationContentService.edit(notificationRequest);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
