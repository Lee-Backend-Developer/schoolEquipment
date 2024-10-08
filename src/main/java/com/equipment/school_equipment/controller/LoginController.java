package com.equipment.school_equipment.controller;

import com.equipment.school_equipment.config.security.UserAdapter;
import com.equipment.school_equipment.repository.MemberRepository;
import com.equipment.school_equipment.request.UserRequest;
import com.equipment.school_equipment.service.LoginUserService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class LoginController {
    private final MemberRepository memberRepository;
    private final LoginUserService loginUserService;

    @GetMapping("login")
    public String getLogin(@ModelAttribute(binding = false, name = "userRequest") UserRequest userRequest) {
        return "member/login";
    }

    @GetMapping("register")
    public String getRegister(Model model) {
        model.addAttribute("userRequest", UserRequest.builder().build());
        return "member/register";
    }

    @PostMapping("register")
    public String postRegister(@Valid @ModelAttribute(binding = false) UserRequest userRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "member/register";
        }
        try{
            loginUserService.create(userRequest);
        } catch (DuplicateRequestException e) {
            bindingResult.addError(new ObjectError("error", e.getMessage()));
            return "member/register";
        }
        return "redirect:/member/login";
    }

    @GetMapping("account")
    public String getAccount(@AuthenticationPrincipal UserAdapter userDetails, Model model){
        UserRequest userRequest = memberRepository.findByUserId(userDetails.getLoginMember().getUserId())
                .map(user -> UserRequest.builder().
                        id(user.getUserId()).passwd(user.getUserPwd())
                        .email(user.getEmail()).name(user.getName())
                        .kakaoTalk(!Objects.isNull(user.getKakaotalkId()))
                        .build())
                .orElseThrow();

        model.addAttribute("userRequest", userRequest);

        return "member/account";
    }

    @PutMapping("account")
    public String putAccount(@AuthenticationPrincipal UserDetails userDetails, @Valid UserRequest userRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors() && userRequest.getId() != null) {
            UserRequest request = UserRequest.builder()
                    .id(userDetails.getUsername()).passwd(userRequest.getPasswd())
                    .email(userRequest.getEmail()).name(userRequest.getName())
                    .kakaoTalk(userRequest.getKakaoTalk())
                    .build();
            model.addAttribute("userRequest", request);
            return "member/account";
        }
        UserRequest request = UserRequest.builder()
                .id(userDetails.getUsername()).passwd(userRequest.getPasswd())
                .email(userRequest.getEmail()).name(userRequest.getName())
                .build();
        loginUserService.update(request);
        return "redirect:/member/logout";
    }
}
