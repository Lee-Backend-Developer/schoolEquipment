package com.equipment.school_equipment.service;

import com.equipment.school_equipment.util.KakaoUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoTalkSerivce implements KakaoUtill {

    @Override
    public Map<String, String> getToken(String code) {
        try {
            URL host = new URL(GET_TOKEN_HOST);
            HttpURLConnection httpURLConnection = (HttpURLConnection) host.openConnection();

            // host header add
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id="+CLIENT_ID);
            stringBuilder.append("&redirect_uri="+REDIRECT_URI);
            stringBuilder.append("&code="+code);

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);


            log.info("service code => {}",httpURLConnection.getResponseMessage());


        } catch (Exception ignored){}


        return null;
    }



    @Override
    public Map<String, Object> getUserInfo(String accessToken) {
        return null;
    }
}
