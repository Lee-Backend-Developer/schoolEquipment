package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.service.KakaoTalkSerivce;
import com.equipment.school_equipment.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @GetMapping("logout")
    public String logout() {
        return "kakaotalk/logout";
    }
}
