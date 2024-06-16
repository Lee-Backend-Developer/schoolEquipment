package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.NotificationContent;
import com.equipment.school_equipment.repository.NotificationContentRepository;
import com.equipment.school_equipment.request.notificationProduct.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationContentService {
    private final NotificationContentRepository notificationContentRepository;

    @Transactional
    public NotificationContent save(NotificationRequest saveNotification) {
        NotificationContent saveNotificationContent = NotificationContent.builder().content(saveNotification.notificationContent()).build();
        return notificationContentRepository.save(saveNotificationContent);
    }

    public void edit(NotificationRequest notificationRequest) {
        NotificationContent findNotificationContent = notificationContentRepository.findById(notificationRequest.id())
                .orElseThrow(NullPointerException::new);

        findNotificationContent.editContent(notificationRequest.notificationContent());
    }
}
