package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
