package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.LoginUser;
import com.equipment.school_equipment.repository.UserRepository;
import com.equipment.school_equipment.request.admin.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class LoginUserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @DisplayName("회원가입 되어야한다.")
    @Test
    void register_O() throws Exception {
        //given 준비 과정
        UserRequest request = UserRequest.builder().id("leeadmin").passwd("1234").build();

        //when 실행
        LoginUser loginUser = userService.create(request);

        //then 검증
        LoginUser findUsers = userRepository.findAll().get(0);
        assertThat(findUsers.getId()).isEqualTo(loginUser.getId());

    }

}