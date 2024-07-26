package com.equipment.school_equipment.util;

import java.util.Map;

public interface KakaoUtill {
    // kakaotalk app restApi
    final String CLIENT_ID = "4e8dd46c24af10ad81dd3cbeb6fa6ba9";
    final String GET_TOKEN_HOST = "https://kauth.kakao.com/oauth/token";
    final String GET_USER_HOST = "https://kapi.kakao.com/v2/user/me";
    final String REDIRECT_URI = "http://localhost/kakaotalk/login";

    public Map<String, String> getToken(String code);

    public Map<String, Object> getUserInfo(String accessToken);
}
