package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.LoginUserConnectLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginUserConnectLogRepository extends JpaRepository<LoginUserConnectLog, Long> {
    List<LoginUserConnectLog> findByMemberKeyId(Long id);
}
