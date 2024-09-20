package com.equipment.school_equipment.config.security;

import com.equipment.school_equipment.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Builder
@Data
public class CustomUserDetails implements UserDetails , OAuth2User {
    private Member loginMember;
    private Map<String, Object> attribute;

    // 일반 로그인 생성자
    public CustomUserDetails(Member loginMember) {
        this.loginMember = loginMember;
    }

    // OAuth2 로그인 생성자
    public CustomUserDetails(Member loginMember, Map<String, Object> attribute) {
        this.loginMember = loginMember;
        this.attribute = attribute;
    }

    @Override
    public String getName() {
        return loginMember.getName();
    }

    //권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> "ROLE_" + loginMember.getRole());
        return collect;
    }

    @Override
    public String getPassword() {
        return loginMember.getUserPwd();
    }

    @Override
    public String getUsername() {
        return loginMember.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }
}
