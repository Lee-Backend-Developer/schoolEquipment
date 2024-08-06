package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.service.KakaoTalkSerivce;
import com.equipment.school_equipment.service.LoginUserService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("kakaotalk")
public class KakaoTalkController {
    private final KakaoTalkSerivce kakaoTalkSerivce;
    private final LoginUserService loginUserService;

    @GetMapping
    public String index() {
        return "kakaotalk/index";
    }

    @GetMapping("login")
    public String getCI(@RequestParam String code) {
        String aceess = kakaoTalkSerivce.getToken(code);    // 인가
        String userInfo = kakaoTalkSerivce.getUserInfo(aceess); // 토큰 조회
        log.info("userinfo {} ", userInfo);
        try {
            LoginUser loginUser = loginUserService.kakaoLogin(userInfo);

        } catch (AuthException e) {
            throw new RuntimeException(e);
        }

        /**
         * todo
         * 카카오톡 고유 ID를 가져온다
         * 고유 ID로 현재 회원이 있는지 조회한다
         * 있다면 로그인, 없다면 회원가입 페이지 이동
         */

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout() {
        return "kakaotalk/logout";
    }
}
