package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

import static jakarta.persistence.GenerationType.IDENTITY;

public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private final String userId;
    private final String userPwd;
    private final UserRole role;

    @Builder
    public User(Long id, String userId, String userPwd, UserRole role) {
        this.id = id;
        this.userId = userId;
        this.userPwd = userPwd;
        this.role = role;
    }
}
