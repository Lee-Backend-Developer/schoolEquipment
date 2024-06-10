package com.equipment.school_equipment.service;

import com.equipment.school_equipment.repository.NotificationProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationProductService {
    private final NotificationProductRepository notificationProductRepository;

}
