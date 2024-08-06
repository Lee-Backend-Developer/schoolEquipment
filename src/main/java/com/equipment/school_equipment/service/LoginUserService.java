package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.domain.enumDomain.UserRole;
import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.request.UserRequest;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    public LoginUser create(UserRequest request) {
        if(userRepository.findByUserId(request.getId()).isPresent()){
          throw new DuplicateRequestException("이미 사용중인 아이디 입니다.");
        }
        LoginUser createLoginUser = LoginUser.builder()
                .userId(request.getId())
                .userPwd(request.getPasswd())
                .name(request.getName())
                .email(request.getEmail())
                .role(UserRole.user)
                .build();
        userRepository.save(createLoginUser);
        return createLoginUser;
    }

    public LoginUser login(UserRequest request) throws AuthException {
        LoginUser loginUser = userRepository.findByUserIdAndUserPwd(request.getId(), request.getPasswd())
                .orElseThrow(() -> new AuthException("없는 사용자 입니다."));

        return loginUser;
    }

    public LoginUser kakaoLogin(String kakaoId) throws AuthException {
        LoginUser loginUser = userRepository.findByKakaotalkId(kakaoId)
                .orElseThrow(() -> new AuthException("없는 사용자 입니다."));

        return loginUser;
    }

    @Transactional
    public void leave(UserRequest request) {
        LoginUser loginUser = userRepository.findByUserIdAndUserPwd(request.getId(), request.getPasswd())
                .orElseThrow(NullPointerException::new);

        userRepository.delete(loginUser);
    }

    @Transactional
    public void update(UserRequest request) {
        LoginUser loginUser = userRepository.findByUserId(request.getId())
                .orElseThrow();

        loginUser.updateUser(request.getPasswd(), request.getName(), request.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));
        UserDetails build = User.builder()
                .username(loginUser.getUserId())
                .password(loginUser.getUserPwd())
                .roles(loginUser.getRole().name())
                .build();

        return build;
    }
}
