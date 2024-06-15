package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationContent;
import com.equipment.school_equipment.repository.NotificationContentRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class NotificationContentServiceTest {
    @Autowired
    private NotificationContentRepository notificationContentRepository;
    @Autowired
    private NotificationContentService notificationContentService;

    @DisplayName("유의사항이 만들어져야한다.")
    @Test
    void create_o(){
        //given 준비 과정
        NotificationRequest saveNotification = NotificationRequest.builder()
                .notificationContent("주의하세요.").build();

        //when 실행
        NotificationContent saveNotificationContent = notificationContentService.save(saveNotification);

        //then 검증
        assertThat(notificationContentRepository.findAll().size()).isEqualTo(1);
        assertThat(saveNotificationContent.getContent()).isEqualTo(saveNotification.notificationContent());
    }

}