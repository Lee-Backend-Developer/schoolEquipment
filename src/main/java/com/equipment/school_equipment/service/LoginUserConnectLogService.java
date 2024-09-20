package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.member.Member;
import com.equipment.school_equipment.domain.LoginUserConnectLog;
import com.equipment.school_equipment.repository.LoginUserConnectLogRepository;
import com.equipment.school_equipment.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserConnectLogService {
    private final LoginUserConnectLogRepository loginUserConnectLogRepository;
    private final MemberRepository memberRepository;

    /**
     * 로그인 사용자 연결 로그 생성
     * @param ip IP address
     * @param id Member ID
     * @return LoginUserConnectLog
     */
    @Transactional
    public LoginUserConnectLog createLoginUserConnect(String ip, String id) {
        Member loginMember = memberRepository.findByUserId(id).orElseThrow(NullPointerException::new);
        LoginUserConnectLog createLoginUserConnect = LoginUserConnectLog.builder()
                .connectIp(ip)
                .connectDate(new Timestamp(new Date().getTime()))
                .memberKey(loginMember)
                .build();

        return loginUserConnectLogRepository.save(createLoginUserConnect);
    }

    /**
     * 로그인 사용자 IP로그 조회
     * @param memberKeyId 사용자 Key ID
     * @return List<LoginUserConnectLog>
     */
    public List<LoginUserConnectLog> findById(Long memberKeyId) {
        return loginUserConnectLogRepository.findByMemberKeyId(memberKeyId);
    }
}
