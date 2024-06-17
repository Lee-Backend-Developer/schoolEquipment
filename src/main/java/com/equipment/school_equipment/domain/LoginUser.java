package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String userId;
    private String userPwd;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public LoginUser(Long id, String userId, String userPwd, UserRole role) {
        this.id = id;
        this.userId = userId;
        this.userPwd = userPwd;
        this.role = role;
    }
}
