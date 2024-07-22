package com.equipment.school_equipment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginUserConnectLog {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id")
    private List<LoginUser> loginUserList = new ArrayList<>();

    private Date connectDate;
    private String connectIp;

    @Builder
    public LoginUserConnectLog(Long id, Date connectDate, String connectIp) {
        this.id = id;
        this.connectDate = connectDate;
        this.connectIp = connectIp;
    }

    // 비즈니스 로직
    public void addLoginUser(LoginUser loginUser) {
        this.loginUserList.add(loginUser);
        getLoginUserList().add(loginUser);
    }


}
