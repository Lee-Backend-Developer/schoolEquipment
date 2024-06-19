package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.request.admin.UserRequest;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService implements UserDetailsService {
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

    public LoginUser login(UserRequest request) throws AuthException {
        LoginUser loginUser = userRepository.findByUserIdAndUserPwd(request.id(), request.passwd())
                .orElseThrow(AuthException::new);

        return loginUser;
    }

    @Transactional
    public void leave(UserRequest request) {
        LoginUser loginUser = userRepository.findByUserIdAndUserPwd(request.id(), request.passwd())
                .orElseThrow(NullPointerException::new);

        userRepository.delete(loginUser);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { //서버에서 값을 가지고 옴
        LoginUser loginUser = userRepository.findByUserId(userId).orElseThrow(NullPointerException::new);
        return toUserDetails(loginUser);
    }
    private UserDetails toUserDetails(LoginUser member) {
        return User.builder()
                .username(member.getUserId())
                .password(member.getUserPwd())
                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
                .build();
    }
}
