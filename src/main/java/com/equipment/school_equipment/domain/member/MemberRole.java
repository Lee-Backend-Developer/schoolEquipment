package com.equipment.school_equipment.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {
    user("사용자"),
    admin("관리자");

    private final String role;

    MemberRole(String role) {
        this.role = role;
    }
}
