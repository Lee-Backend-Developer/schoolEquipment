package com.equipment.school_equipment.controller.session;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class SessionObj {
    private Long id;
    private UserRole userRole;

    @Builder
    public SessionObj(Long id, UserRole userRole) {
        this.id = id;
        this.userRole = userRole;
    }
}
