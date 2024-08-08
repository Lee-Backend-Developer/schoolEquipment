package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.service.KakaoTalkSerivce;
import com.equipment.school_equipment.service.LoginUserService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Collections;
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
    public String getCI(@RequestParam String code, @RequestParam String state) {
        log.info("code --> {}", code);
        /*String aceess = kakaoTalkSerivce.getToken(code);    // 인가
        String userInfo = kakaoTalkSerivce.getUserInfo(aceess); // 토큰 조회


        // 현재 등록된 세션 목록 조회
        log.info("userinfo {} ", userInfo);
        try {
            LoginUser loginUser = loginUserService.kakaoLogin(userInfo);

            UserDetails userDetails = loginUserService.loadUserByUsername(loginUser.getUserId());

            new SecurityContextImpl(UsernamePasswordAuthenticationToken.unauthenticated(userDetails, null));

        } catch (AuthException e) {
//            throw new RuntimeException(e);
            return "redirect:/member/login";
        }

        *//**
         * todo
         * 카카오톡 고유 ID를 가져온다
         * 고유 ID로 현재 회원이 있는지 조회한다
         * 있다면 로그인, 없다면 회원가입 페이지 이동*//*

*/
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout() {
        return "kakaotalk/logout";
    }
}
