package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUserConnectLog;
import com.equipment.school_equipment.domain.member.Member;
import com.equipment.school_equipment.repository.LoginUserConnectLogRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@Transactional
@SpringBootTest
class LoginMemberConnectLogServiceTest {
    @Autowired
    private LoginUserConnectLogRepository loginUserConnectLogRepository;
    @Autowired
    private LoginUserConnectLogService loginUserConnectLogService;

    @DisplayName("접속한 A 계정의 접속 로그 IP를 조회함")
    @Test
    void find_O() throws Exception {
        //given 준비
        Member memberA = Member.builder()
                .userId("A")
                .build();
        LoginUserConnectLog saveLoginUserConnectLog = LoginUserConnectLog.builder()
                .memberKey(memberA)
                .connectIp("127.0.0.1")
                .connectDate(new Timestamp(System.currentTimeMillis()))
                .build();
        loginUserConnectLogRepository.save(saveLoginUserConnectLog);
        //when 실행

        //then 검증

    }

}