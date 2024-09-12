package com.equipment.school_equipment.service;

import com.equipment.school_equipment.config.security.UserAdapter;
import com.equipment.school_equipment.domain.user.User;
import com.equipment.school_equipment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

// api 로그인 성공 이후 사용자 정보를 가져올 때의 설정
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final LoginUserService loginUserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserService 실행");
        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        String kakaoId = oAuth2User.getName();
        // kakao 고유 아이디 가져오기

        User loginUser = kakaoIdLogin(kakaoId);

        // 세션 생성
        return new UserAdapter(loginUser, oAuth2User.getAttributes());
    }
    private User kakaoIdLogin(String kakaoId){
        User loginUser = userRepository.findByKakaotalkId(kakaoId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));
        return loginUser;
    }


}