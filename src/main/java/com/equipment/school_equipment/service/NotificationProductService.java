package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.repository.NotificationProductRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
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
    public static String UPLOAD_DIRECTORY = "/Users/leemac/IdeaProjects/img/" + "mainPage";


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
        // 이미지 파일 추가
        String fileContentType = Objects.requireNonNull(editRequest.imageFile().getOriginalFilename()).split("\\.")[0];
        String fileName = UUID.randomUUID() + "." + fileContentType;

        Path path = Paths.get(UPLOAD_DIRECTORY, fileName);   // 절대경로, 이미지 저장할 이름
        try {
            Files.write(path, editRequest.imageFile().getBytes());   // path 경로에 이미지 저장
        } catch (IOException ignored){};

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
