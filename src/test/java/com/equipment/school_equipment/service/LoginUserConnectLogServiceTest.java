package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUserConnectLog;
import com.equipment.school_equipment.repository.LoginUserConnectLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUserConnectLogServiceTest {
    @InjectMocks
    LoginUserConnectLogService loginUserConnectLogService;

    @Mock
    LoginUserConnectLogRepository loginUserConnectLogRepository;


    @DisplayName("로그인이 될 경우 접속자 아이피를 로그 생성")
    @Test
    void login_o() throws Exception {
        //given 준비 과정
        String actualIp = "127.0.0.1";

        //when 실행

        when(loginUserConnectLogRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(LoginUserConnectLog.builder()
                        .id(1L)
                        .connectIp("127.0.0.1")
                        .connectDate(new Timestamp(new Date().getTime()))
                        .build()));

        LoginUserConnectLog loginUserConnect = loginUserConnectLogService.createLoginUserConnect("id", "pwd");

        //then 검증
        LoginUserConnectLog loginUserConnectLog = loginUserConnectLogRepository.findById(1L).get();
        assertEquals(loginUserConnect.getConnectIp(), actualIp);
    }


}