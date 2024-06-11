package com.equipment.school_equipment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NotificationProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String content;
    private String img;

    @Builder
    public NotificationProduct(Long id, String subject, String content, String img) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.img = img;
    }

    // 비즈니스 로직

    public void edit(String subject, String content, String img) {
        this.subject = subject;
        this.content = content;
        this.img = img;
    }
}
