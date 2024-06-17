package com.equipment.school_equipment.domain.enumDomain;

import lombok.Getter;

@Getter
public enum UserRole {
    user("사용자"),
    admin("관리자");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
