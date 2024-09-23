package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUserConnectLog;
import com.equipment.school_equipment.domain.member.Member;
import com.equipment.school_equipment.repository.LoginUserConnectLogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginMemberConnectLogServiceTest {
    @Mock
    private LoginUserConnectLogRepository loginUserConnectLogRepository;
    @InjectMocks
    private LoginUserConnectLogService loginUserConnectLogService;


    @DisplayName("접속한 A 계정의 접속 로그 IP를 조회함")
    @Test
    void find_O() throws Exception {
        //given 준비

        Member memberA = Member.builder() // spy
                .id(1L)
                .userId("A")
                .build();

        LoginUserConnectLog saveLoginUserConnectLog = LoginUserConnectLog.builder() // spy
                .memberKey(memberA)
                .connectIp("127.0.0.1")
                .connectDate(new Timestamp(System.currentTimeMillis()))
                .build();

        // stubbing
        given(loginUserConnectLogRepository.findByMemberKeyId(any(Long.class)))
                .willReturn(List.of(saveLoginUserConnectLog));

        //when 실행
        List<LoginUserConnectLog> findById = loginUserConnectLogService.findById(memberA.getId());

        //then 검증
        Assertions.assertThat(findById.size()).isEqualTo(1);

    }

}