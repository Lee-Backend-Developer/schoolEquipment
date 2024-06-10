package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.repository.NotificationProductRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationProductService {
    private final NotificationProductRepository notificationProductRepository;

    @Transactional
    public NotificationProduct save(NotificationCreate requestForm) {
        NotificationProduct saveProduct = NotificationProduct.builder()
                .img(requestForm.img())
                .content(requestForm.content())
                .subject(requestForm.subject())
                .build();
        return notificationProductRepository.save(saveProduct);
    }
}
