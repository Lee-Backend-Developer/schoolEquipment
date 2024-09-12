package com.equipment.school_equipment.service;

import com.equipment.school_equipment.config.security.UserAdapter;
import com.equipment.school_equipment.domain.user.User;
import com.equipment.school_equipment.domain.user.UserRole;
import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.request.UserRequest;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
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
    public User create(UserRequest request) {
        if(userRepository.findByUserId(request.getId()).isPresent()){
          throw new DuplicateRequestException("이미 사용중인 아이디 입니다.");
        }
        User createLoginUser = User.builder()
                .userId(request.getId())
                .userPwd(request.getPasswd())
                .name(request.getName())
                .email(request.getEmail())
                .role(UserRole.user)
                .build();
        userRepository.save(createLoginUser);
        return createLoginUser;
    }

    public User login(UserRequest request) throws AuthException {
        User loginUser = userRepository.findByUserIdAndUserPwd(request.getId(), request.getPasswd())
                .orElseThrow(() -> new AuthException("없는 사용자 입니다."));

        return loginUser;
    }

    public User kakaoLogin(String kakaoId) throws AuthException {
        User loginUser = userRepository.findByKakaotalkId(kakaoId)
                .orElseThrow(() -> new AuthException("없는 사용자 입니다."));

        return loginUser;
    }

    @Transactional
    public void leave(UserRequest request) {
        User loginUser = userRepository.findByUserIdAndUserPwd(request.getId(), request.getPasswd())
                .orElseThrow(NullPointerException::new);

        userRepository.delete(loginUser);
    }

    @Transactional
    public void update(UserRequest request) {
        User loginUser = userRepository.findByUserId(request.getId())
                .orElseThrow();

        loginUser.updateUser(request.getPasswd(), request.getName(), request.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loginUser = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));
         return new UserAdapter(loginUser);
    }
}
