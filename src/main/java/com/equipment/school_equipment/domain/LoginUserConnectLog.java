package com.equipment.school_equipment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginUserConnectLog {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private LoginUser userKey;

    private Timestamp connectDate;
    private String connectIp;

    @Builder
    public LoginUserConnectLog(Long id, Timestamp connectDate, String connectIp, LoginUser userKey) {
        this.id = id;
        this.connectDate = connectDate;
        this.connectIp = connectIp;
        this.userKey = userKey;
    }
}
