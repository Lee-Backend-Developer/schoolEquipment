package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.response.api.NotificationProductResponse;
import com.equipment.school_equipment.service.NotificationProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/notification")
public class NotificationProductApiController {

    private final NotificationProductService notificationProductService;

    @GetMapping
    public ResponseEntity<List<NotificationProductResponse>> getNotification(){
        List<NotificationProductResponse> responseList = notificationProductService.finds().stream()
                .map(notificationProduct -> NotificationProductResponse.builder()
                        .id(notificationProduct.getId())
                        .img(notificationProduct.getImg())
                        .subject(notificationProduct.getSubject())
                        .content(notificationProduct.getContent())
                        .build())
                .toList();
        return ResponseEntity.ok(responseList);
    }
}
