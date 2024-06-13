package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationProduct;
import com.equipment.school_equipment.repository.NotificationProductRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class NotificationProductServiceTest {
    @Autowired
    private NotificationProductService notificationProductService;
    @Autowired
    private NotificationProductRepository notificationProductRepository;

    @DisplayName("공지가 생성이 되어야한다.")
    @Test
    void create_O() throws Exception {
        //given 준비 과정
        NotificationRequest requestForm = NotificationRequest.builder()
                .subject("제목").content("내용").imageFile(null).build();
        //when 실행
        NotificationProduct saveNotificationProduct = notificationProductService.save(requestForm);

        //then 검증
        assertThat(notificationProductRepository.findAll().size()).isEqualTo(1);
        assertThat(notificationProductRepository.findAll().get(0).getContent())
                .isEqualTo(saveNotificationProduct.getContent());
    }

    @DisplayName("만들어진 공지가 있으면 1개 조회가 되어야한다.")
    @Test
    void find_one_o() throws Exception {
        //given 준비 과정
        NotificationProduct requestForm = NotificationProduct.builder()
                .subject("제목").content("내용").img("이미지경로")
                .build();
        NotificationProduct notificationProduct = notificationProductRepository.save(requestForm);

        //when 실행
        List<NotificationProduct> notificationProductList = notificationProductService.findList();

        //then 검증
        assertThat(notificationProductRepository.findAll().size()).isEqualTo(1);
        assertThat(notificationProductList.size()).isEqualTo(1);
    }

    @DisplayName("만들어진 공지가 ID 1번이 조회가 되어야한다.")
    @Test
    void findById_O() throws Exception {
        //given 준비 과정
        NotificationProduct requestForm = NotificationProduct.builder()
                .id(1L)
                .subject("제목").content("내용").img("이미지경로")
                .build();
        NotificationProduct notificationProduct = notificationProductRepository.save(requestForm);
        //when 실행
        NotificationProduct findByNotification = notificationProductService.findById(1L);
        //then 검증
        assertThat(findByNotification.getSubject()).isEqualTo(requestForm.getSubject());
    }

    @DisplayName("생성된 공지에 제목이 수정 되어야한다.")
    @Test
    void update_O() throws Exception {
        //given 준비 과정
        NotificationProduct requestForm = NotificationProduct.builder()
                .subject("제목").content("내용").img("이미지경로")
                .build();
        NotificationProduct saveNotificationProduct = notificationProductRepository.save(requestForm);

        //when 실행
        NotificationRequest editRequest = NotificationRequest.builder()
                .id(saveNotificationProduct.getId())
                .subject("수정").build();
        notificationProductService.edit(editRequest);

        //then 검증
        assertThat(notificationProductRepository.findAll().get(0).getSubject())
                .isEqualTo("수정");
    }

    @DisplayName("공지가 삭제 되어야한다.")
    @Test
    void delete_O() throws Exception {
        //given 준비 과정
        NotificationProduct requestForm = NotificationProduct.builder()
                .subject("제목").content("내용").img("이미지경로")
                .build();
        NotificationProduct saveNotificationProduct = notificationProductRepository.save(requestForm);

        //when 실행
        notificationProductService.delete(saveNotificationProduct.getId());

        //then 검증
        assertThat(notificationProductRepository.findAll().size()).isEqualTo(0);


    }

}