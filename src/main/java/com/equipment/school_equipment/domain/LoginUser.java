package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String kakaotalkId;

    @Builder
    public LoginUser(Long id, String userId, String userPwd, String name, String email, UserRole role, String kakaotalkId) {
        this.id = id;
        this.userId = userId;
        this.userPwd = userPwd;
        this.name = name;
        this.email = email;
        this.role = role;
        this.kakaotalkId = kakaotalkId;
    }

    //비즈니스 로직
    public void updateUser(String userPwd, String name, String email){
        this.userPwd = userPwd;
        this.name = name;
        this.email = email;
    }
}
