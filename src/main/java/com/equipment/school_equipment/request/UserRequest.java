package com.equipment.school_equipment.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserRequest(
        @NotEmpty
        String id,
        @NotEmpty
        String passwd,
        @NotEmpty
        String chkPasswd,
        @NotEmpty
        String name,
        @NotEmpty @Email
        String email
) { }
