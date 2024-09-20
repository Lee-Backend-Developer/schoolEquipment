package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserIdAndUserPwd(String userId, String userPwd);
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByKakaotalkId(String kakaotalkId);
}
