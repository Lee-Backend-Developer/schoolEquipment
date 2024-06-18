package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.controller.session.SessionObj;
import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.request.admin.UserRequest;
import com.equipment.school_equipment.service.LoginUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class LoginController {

    private final LoginUserService loginUserService;
    private final HttpSession httpSession;

    @GetMapping("login")
    public String getLogin(@ModelAttribute(binding = false, name = "userRequest") UserRequest userRequest) {

        return Objects.isNull(httpSession.getAttribute("clientSession")) ? "member/login" : "redirect:/";
    }

    @PostMapping("login")
    public String postLogin(
            @Valid UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        LoginUser login = loginUserService.login(userRequest);
        httpSession.setMaxInactiveInterval(0);
        httpSession.setAttribute("clientSession", login);

        return "redirect:/";
    }

    @GetMapping("logout")
    public String getLogout() {
        httpSession.invalidate();
        return "redirect:/";
    }


}
