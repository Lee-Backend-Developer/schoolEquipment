package com.equipment.school_equipment.config.security;

import com.equipment.school_equipment.domain.user.User;
import lombok.Getter;

import java.util.Map;

@Getter
public class UserAdapter extends CustomUserDetails {

    private User loginUser;
    private Map<String, Object> attributes;

    public UserAdapter(User loginUser){
        super(loginUser);
        this.loginUser = loginUser;
    }

    public UserAdapter(User loginUser, Map<String, Object> attributes){
        super(loginUser, attributes);
        this.loginUser = loginUser;
        this.attributes = attributes;
    }
}