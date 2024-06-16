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

    @DisplayName("유의사항이 조회가 되어야함")
    @Test
    void find_o() throws Exception {
        //given 준비 과정
        NotificationContent saveContent = NotificationContent.builder().content("이 제품 입니다.").build();
        notificationContentRepository.save(saveContent);

        //when 실행
        NotificationContent finds = notificationContentService.finds();

        //then 검증
        assertThat(finds).isNotNull();

    }

    @DisplayName("유의사항 수정이 되어야한다.")
    @Test
    void edit_O() throws Exception {
        //given 준비 과정
        NotificationContent saveContent = NotificationContent.builder().content("이 제품 입니다.").build();
        notificationContentRepository.save(saveContent);

        String edit = "수정이 되어야한다";

        //when 실행
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .id(saveContent.getId())
                .notificationContent(edit).build();

        notificationContentService.edit(notificationRequest);

        //then 검증
        NotificationContent findNotification = notificationContentRepository.findAll().get(0);
        assertThat(findNotification.getContent()).isEqualTo(edit);
    }

}