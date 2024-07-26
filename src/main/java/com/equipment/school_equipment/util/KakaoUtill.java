package com.equipment.school_equipment.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public interface KakaoUtill extends JsonUtill {
    // kakaotalk app restApi
    final String CLIENT_ID = "4e8dd46c24af10ad81dd3cbeb6fa6ba9";
    final String GET_TOKEN_HOST = "https://kauth.kakao.com/oauth/token";
    final String GET_USER_HOST = "https://kapi.kakao.com/v2/user/me";
    final String REDIRECT_URI = "http://localhost/kakaotalk/login";

    String getToken(String code);

    Map<String, Object> getUserInfo(String accessToken);

    private HttpURLConnection getOpenConnection(String host) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(host).openConnection();
            // host header add
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        } catch(java.io.IOException |  NullPointerException nullPointerException) {
            nullPointerException.fillInStackTrace();
        }
        return httpURLConnection;
    }

    default String postBodySend(String host, StringBuilder stringBuilder) {
        try {
            HttpURLConnection openConnection = getOpenConnection(host);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(openConnection.getOutputStream()));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            return getResponseBody(openConnection);

        } catch (IOException e) {
            e.fillInStackTrace();
            return e.getMessage();
        }
    }

    private String getResponseBody(HttpURLConnection openConnection) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            return br.readLine();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
