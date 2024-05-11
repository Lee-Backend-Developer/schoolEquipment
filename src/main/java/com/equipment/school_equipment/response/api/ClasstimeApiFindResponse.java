package com.equipment.school_equipment.response.api;

import lombok.Builder;

@Builder
public record ClasstimeApiFindResponse(
        Long id,
        String classNames
) {
}
