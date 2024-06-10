package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.repository.NotificationProductRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationProductService {
    private final NotificationProductRepository notificationProductRepository;

    @Transactional
    public NotificationProduct save(NotificationRequest requestForm) {
        NotificationProduct saveProduct = NotificationProduct.builder()
                .img(requestForm.img())
                .content(requestForm.content())
                .subject(requestForm.subject())
                .build();
        return notificationProductRepository.save(saveProduct);
    }

    @Transactional
    public void edit(NotificationRequest editRequest) {
        NotificationProduct notificationProduct = notificationProductRepository.findById(editRequest.id()).orElseThrow(NullPointerException::new);
        notificationProduct.edit(editRequest.subject(), editRequest.content(), editRequest.img());
    }
}
