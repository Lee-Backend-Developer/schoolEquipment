package com.equipment.school_equipment.config.security;

import com.equipment.school_equipment.domain.member.Member;
import lombok.Getter;

import java.util.Map;

@Getter
public class UserAdapter extends CustomUserDetails {

    private Member loginMember;
    private Map<String, Object> attributes;

    public UserAdapter(Member loginMember){
        super(loginMember);
        this.loginMember = loginMember;
    }

    public UserAdapter(Member loginMember, Map<String, Object> attributes){
        super(loginMember, attributes);
        this.loginMember = loginMember;
        this.attributes = attributes;
    }
}