package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.request.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginLoginUserServiceTestMockito {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginUserService loginUserService;

    @DisplayName("회원가입 되어야한다.")
    @Test
    void register_O() throws Exception {
        //given 준비 과정
        UserRequest request = UserRequest.builder().id("leeadmin").passwd("1234").build();

        //when 실행
        LoginUser createLoginuser = LoginUser.builder().id(1L).userId("leeadmin").userPwd("1234").build();

        lenient().when(loginUserService.create(request)).thenReturn(createLoginuser);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(createLoginuser));

        //then 검증
        LoginUser findUsers = userRepository.findById(1L).get();
        assertThat(findUsers).isNotNull();
        assertThat(findUsers.getUserId()).isEqualTo(createLoginuser.getUserId());
    }

    @DisplayName("로그인 되어야함")
    @Test
    void login_o() throws Exception {
        //given 준비 과정
        UserRequest request = UserRequest.builder()
                .id("leeadmin")
                .passwd("1234")
                .build();

        //when 실행
        when(userRepository.findByUserIdAndUserPwd(anyString(),anyString()))
                .thenReturn(Optional.ofNullable(LoginUser.builder().userId("leeadmin").build()));

        LoginUser login = loginUserService.login(request);

        //then 검증
        assertThat(login.getUserId()).isEqualTo(request.id());
    }

    @DisplayName("회원탈퇴 되어야함")
    @Test
    void leave_o() throws Exception {
        //given 준비 과정
        LoginUser saveLoginUser = LoginUser.builder()
                .userId("leeadmin").userPwd("1234")
                .build();

        UserRequest request = UserRequest.builder()
                .id("leeadmin")
                .passwd("1234")
                .build();

        //when 실행
        assertThat(userRepository.findByUserIdAndUserPwd(request.id(), request.passwd()))
                .isNull();
        lenient().when(userRepository.findByUserIdAndUserPwd(anyString(), anyString()))
                .thenReturn(Optional.of(LoginUser.builder().build()));

        loginUserService.leave(request);
        //then 검증
        assertThat(userRepository.findByUserIdAndUserPwd(request.id(), request.passwd()))
                .isNotNull();
    }

}