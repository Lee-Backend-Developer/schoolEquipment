package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.request.admin.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService {
    private final UserRepository userRepository;

    @Transactional
    public LoginUser create(UserRequest request) {
        LoginUser createLoginUser = LoginUser.builder()
                .userId(request.id())
                .userPwd(request.passwd())
                .build();
        userRepository.save(createLoginUser);
        return createLoginUser;
    }

    public LoginUser login(UserRequest request) {
        LoginUser loginUser = userRepository.findByUserIdAndUserPwd(request.id(), request.passwd())
                .orElseThrow(NullPointerException::new);

        return loginUser;
    }

    @Transactional
    public void leave(UserRequest request) {
        LoginUser loginUser = userRepository.findByUserIdAndUserPwd(request.id(), request.passwd())
                .orElseThrow(NullPointerException::new);

        userRepository.delete(loginUser);

    }
}