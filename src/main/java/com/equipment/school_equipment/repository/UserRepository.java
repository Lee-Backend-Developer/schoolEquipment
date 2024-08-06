package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByUserIdAndUserPwd(String userId, String userPwd);
    Optional<LoginUser> findByUserId(String userId);
    Optional<LoginUser> findByKakaotalkId(String kakaotalkId);
}
