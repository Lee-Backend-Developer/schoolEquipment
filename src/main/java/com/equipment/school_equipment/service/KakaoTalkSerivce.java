package com.equipment.school_equipment.service;

import com.equipment.school_equipment.util.KakaoUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoTalkSerivce implements KakaoUtill {

    /**
     * 카카오톡 로그인 요청
     * @param code 카카오톡 인증 코드
     * @return token value
     */
    @Override
    public String getToken(String code) {
        StringBuilder bodyParam = new StringBuilder();
        bodyParam.append("grant_type=authorization_code");
        bodyParam.append("&client_id=" + CLIENT_ID);
        bodyParam.append("&redirect_uri=" + REDIRECT_URI);
        bodyParam.append("&code=" + code);

        String value = postBodySend(GET_TOKEN_HOST, bodyParam);
        log.info("response Message => {}", stringToObject(value).getAccess_token());

        return stringToObject(value).getAccess_token();
    }

    /**
     * 카카오톡 유저 정보 요청
     * @param accessToken token value
     * @return 카카오톡 유저 고유 ID
     */
    @Override
    public String getUserInfo(String accessToken) {
        String response = getUserId(accessToken);

        return stringToObject(response).getId();
    }


}

