package com.equipment.school_equipment.service;

import com.equipment.school_equipment.repository.NotificationProductRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NotificationProductServiceTest {
    @Autowired
    private NotificationProductService notificationProductService;
    @Autowired
    private NotificationProductRepository notificationProductRepository;

    @DisplayName("공지가 생성이 되어야한다.")
    @Test
    void create_O() throws Exception {
        //given 준비 과정
        NotificationCreate requestForm = NotificationCreate.builder()
                .subject("제목").content("내용").img("이미지경로").build();
        //when 실행
        notificationProductService.save(requestForm);

        //then 검증
        Assertions.assertThat(notificationProductRepository.findAll().size()).isEqualTo(1);
    }

}