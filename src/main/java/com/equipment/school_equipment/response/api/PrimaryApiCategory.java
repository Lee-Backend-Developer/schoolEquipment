package com.equipment.school_equipment.response.api;

import lombok.Builder;

public record PrimaryApiCategory(
        Long id,
        String name
) {

    @Builder
    public PrimaryApiCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
