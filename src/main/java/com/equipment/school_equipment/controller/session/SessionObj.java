package com.equipment.school_equipment.controller.session;

import com.equipment.school_equipment.domain.enumDomain.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class SessionObj {
    private String id;
    private UserRole userRole;
}
