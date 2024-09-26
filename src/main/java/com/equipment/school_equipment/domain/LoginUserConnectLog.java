package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class LoginUserConnectLog {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberKey;

    @Builder.Default
    private Timestamp connectDate = new Timestamp(System.currentTimeMillis());
    @Builder.Default
    private String connectIp = "127.0.0.1";

    public LoginUserConnectLog(Long id, Member memberKey, Timestamp connectDate, String connectIp) {
        this.id = id;
        this.memberKey = memberKey;
        this.connectDate = connectDate;
        this.connectIp = connectIp;
    }
}
