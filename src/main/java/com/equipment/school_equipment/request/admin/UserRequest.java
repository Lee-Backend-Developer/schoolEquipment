package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import lombok.Builder;

public record UserRequest(
        String id,
        String passwd,
        UserRole userRole
) {

    @Builder
    public UserRequest(String id, String passwd, UserRole userRole) {
        this.id = id;
        this.passwd = passwd;
        this.userRole = UserRole.user;
    }
}
