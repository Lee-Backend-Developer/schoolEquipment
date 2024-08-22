package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.repository.NotificationProductRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import com.equipment.school_equipment.util.FileSaveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationProductService {

    private final NotificationProductRepository notificationProductRepository;

    public List<NotificationProduct> findList() {
        return notificationProductRepository.findAll();
    }

    public NotificationProduct findById(Long id){
        return notificationProductRepository.findById(id)
                .orElseThrow(NullPointerException::new);
    }

    @Transactional
    public NotificationProduct save(NotificationRequest requestForm) {
        NotificationProduct saveProduct = NotificationProduct.builder()
                .img(requestForm.imageFile().getOriginalFilename())
                .content(requestForm.content())
                .subject(requestForm.subject())
                .build();
        return notificationProductRepository.save(saveProduct);
    }

    @Transactional
    public void edit(NotificationRequest editRequest) {
        String fileName = "";
        if(!editRequest.imageFile().isEmpty()) {
            fileName = FileSaveUtil.fileSave(editRequest.imageFile(), FileSaveUtil.PATH_MAINPAGE);
        }

        NotificationProduct notificationProduct = notificationProductRepository.findById(editRequest.id()).orElseThrow(NullPointerException::new);
        notificationProduct.edit(
                editRequest.subject(),
                editRequest.content(),
                editRequest.imageFile().isEmpty() ? notificationProduct.getImg() : fileName
        );
    }

    @Transactional
    public void delete(Long id) {
        NotificationProduct notificationProduct = notificationProductRepository.findById(id).orElseThrow(NullPointerException::new);
        notificationProductRepository.delete(notificationProduct);
    }
}
