package com.equipment.school_equipment.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public interface KakaoUtill extends JsonUtill {
    // kakaotalk app restApi
    final String CLIENT_ID = "4e8dd46c24af10ad81dd3cbeb6fa6ba9";
    final String GET_TOKEN_HOST = "https://kauth.kakao.com/oauth/token";
    final String GET_USER_HOST = "https://kapi.kakao.com/v2/user/me";
    final String REDIRECT_URI = "http://localhost/kakaotalk/login";

    String getToken(String code);

    String getUserInfo(String accessToken);

    default String getUserId(String accessToken) {
        try {
            HttpURLConnection openConnection = getOpenConnection(GET_USER_HOST);
            openConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            openConnection.addRequestProperty("Authorization", "Bearer " + accessToken);

            return getResponseBody(openConnection);
        } catch (Exception e) {
            e.fillInStackTrace();
            return e.getMessage();
        }
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


    private String getResponseBody(HttpURLConnection openConnection) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            return br.readLine();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
