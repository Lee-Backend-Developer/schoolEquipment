package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.user.User;
import com.equipment.school_equipment.domain.LoginUserConnectLog;
import com.equipment.school_equipment.repository.LoginUserConnectLogRepository;
import com.equipment.school_equipment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserConnectLogService {
    private final LoginUserConnectLogRepository loginUserConnectLogRepository;
    private final UserRepository userRepository;

    /**
     * 로그인 사용자 연결 로그 생성
     * @param ip IP address
     * @param id Member ID
     * @return LoginUserConnectLog
     */
    @Transactional
    public LoginUserConnectLog createLoginUserConnect(String ip, String id) {
        User loginUser = userRepository.findByUserId(id).orElseThrow(NullPointerException::new);
        LoginUserConnectLog createLoginUserConnect = LoginUserConnectLog.builder()
                .connectIp(ip)
                .connectDate(new Timestamp(new Date().getTime()))
                .userKey(loginUser)
                .build();

        return loginUserConnectLogRepository.save(createLoginUserConnect);
    }
}
