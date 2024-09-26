package com.equipment.school_equipment.service;

import com.equipment.school_equipment.config.security.UserAdapter;
import com.equipment.school_equipment.domain.member.Member;
import com.equipment.school_equipment.domain.member.MemberRole;
import com.equipment.school_equipment.message.error.Message;
import com.equipment.school_equipment.repository.MemberRepository;
import com.equipment.school_equipment.request.UserRequest;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param request UserRequest
     * @return User
     */
    @Transactional
    public Member create(UserRequest request) throws RuntimeException {
        if(memberRepository.findByUserId(request.getId()).isPresent()){
          throw new DuplicateRequestException(Message.Member_Duplicate_ERROR);
        }
        Member createLoginMember = Member.builder()
                .userId(request.getId())
                .userPwd(request.getPasswd())
                .name(request.getName())
                .email(request.getEmail())
                .role(MemberRole.user)
                .build();
        memberRepository.save(createLoginMember);
        return createLoginMember;
    }

    /**
     * 로그인
     * @param request UserRequest
     * @return User
     * @throws AuthException 사용자가 없을 때
     */
    public Member login(UserRequest request) throws AuthException {
        Member loginMember = getByUserIdAndUserPwd(request);

        return loginMember;
    }

    @Transactional
    public void leave(UserRequest request) throws AuthException {
        Member loginMember = getByUserIdAndUserPwd(request);

        memberRepository.delete(loginMember);
    }

    /**
     * 회원 정보 수정
     * @param request UserRequest
     */
    @Transactional
    public void update(UserRequest request) {
        Member loginMember = memberRepository.findByUserId(request.getId())
                .orElseThrow();

        loginMember.updateUser(request.getPasswd(), request.getName(), request.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member loginMember = memberRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));
         return new UserAdapter(loginMember);
    }

    private Member getByUserIdAndUserPwd(UserRequest request) throws AuthException {
        return memberRepository.findByUserIdAndUserPwd(request.getId(), request.getPasswd())
                .orElseThrow(() -> new AuthException(Message.MEMBER_FIND_ERROR));
    }
}
