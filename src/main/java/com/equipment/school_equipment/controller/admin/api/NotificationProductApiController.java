package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import com.equipment.school_equipment.response.api.NotificationProductResponse;
import com.equipment.school_equipment.service.NotificationProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/notification")
public class NotificationProductApiController {

    private final NotificationProductService notificationProductService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationProductResponse> getNotification(@PathVariable Long id){
        NotificationProduct findNotification = notificationProductService.findById(id);
        NotificationProductResponse response = NotificationProductResponse.builder()
                .id(findNotification.getId())
                .img(findNotification.getImg())
                .subject(findNotification.getSubject())
                .content(findNotification.getContent())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NotificationProductResponse>> getNotification(){
        List<NotificationProductResponse> responseList = notificationProductService.findList().stream()
                .map(notificationProduct -> NotificationProductResponse.builder()
                        .id(notificationProduct.getId())
                        .img(notificationProduct.getImg())
                        .subject(notificationProduct.getSubject())
                        .content(notificationProduct.getContent())
                        .build())
                .toList();
        return ResponseEntity.ok(responseList);
    }

    /**
     * API -> 주소 리다이렉트 위해서 ModelAndView 객체사용
     */
    @PutMapping
    public ModelAndView putNotification(@ModelAttribute NotificationRequest requestForm) {
        notificationProductService.edit(requestForm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/notification");
        return modelAndView;
    }
}
