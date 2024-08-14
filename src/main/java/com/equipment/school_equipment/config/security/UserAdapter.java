package com.equipment.school_equipment.config.security;

import com.equipment.school_equipment.domain.LoginUser;
import lombok.Getter;

import java.util.Map;

@Getter
public class UserAdapter extends CustomUserDetails {

    private LoginUser loginUser;
    private Map<String, Object> attributes;

    public UserAdapter(LoginUser loginUser){
        super(loginUser);
        this.loginUser = loginUser;
    }

    public UserAdapter(LoginUser loginUser, Map<String, Object> attributes){
        super(loginUser, attributes);
        this.loginUser = loginUser;
        this.attributes = attributes;
    }
}