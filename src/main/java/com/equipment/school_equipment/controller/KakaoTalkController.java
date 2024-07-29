package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.service.KakaoTalkSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    public String index() {
        return "kakaotalk/index";
    }

    @GetMapping("login")
    public String getCI(@RequestParam String code) {
        String aceess = kakaoTalkSerivce.getToken(code);
        String userInfo = kakaoTalkSerivce.getUserInfo(aceess);
        log.info("userinfo {} ", userInfo);

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout() {
        return "kakaotalk/logout";
    }
}
