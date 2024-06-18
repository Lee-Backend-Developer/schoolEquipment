package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.controller.session.SessionObj;
import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.request.admin.UserRequest;
import com.equipment.school_equipment.service.LoginUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class LoginController {

    private final LoginUserService loginUserService;
    
    @GetMapping("login")
    public String getLogin(@ModelAttribute(binding = false, name = "userRequest") UserRequest userRequest) {
        return "member/login";
    }

    @PostMapping("login")
    public String postLogin(
            @Valid UserRequest userRequest, BindingResult bindingResult, HttpSession httpSession){
        if(bindingResult.hasErrors()) {
            return "member/login";
        }
        LoginUser login = loginUserService.login(userRequest);
        httpSession.setAttribute("client", login);

        return "redirect:/";
    }



}
