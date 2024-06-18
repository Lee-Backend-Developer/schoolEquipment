package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

public record UserRequest(
        @NotEmpty
        String id,
        @NotEmpty
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
