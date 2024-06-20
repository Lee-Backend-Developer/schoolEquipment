package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.controller.session.SessionObj;
import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.request.admin.UserRequest;
import com.equipment.school_equipment.service.LoginUserService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class LoginController {

    @GetMapping("login")
    public String getLogin(@ModelAttribute(binding = false, name = "userRequest") UserRequest userRequest) {

        return "member/login";
    }

  /*  @GetMapping("logout")
    public String getLogout() {
        httpSession.invalidate();
        return "redirect:/";
    }*/


}
