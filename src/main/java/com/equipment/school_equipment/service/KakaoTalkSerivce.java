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

    @Override
    public String getToken(String code) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("grant_type=authorization_code");
        stringBuilder.append("&client_id=" + CLIENT_ID);
        stringBuilder.append("&redirect_uri=" + REDIRECT_URI);
        stringBuilder.append("&code=" + code);

        String value = postBodySend(GET_TOKEN_HOST, stringBuilder);
        log.info("response Message => {}", stringToObject(value).getAccess_token());

        return stringToObject(value).getAccess_token();
    }

    @Override
    public String getUserInfo(String accessToken) {

        String response = getUserId(accessToken);
        log.info("response ID => {}", stringToObject(response).getId());

        return response;
    }


}

