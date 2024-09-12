package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndUserPwd(String userId, String userPwd);
    Optional<User> findByUserId(String userId);
    Optional<User> findByKakaotalkId(String kakaotalkId);
}
