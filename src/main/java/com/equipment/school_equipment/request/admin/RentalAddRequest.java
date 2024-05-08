package com.equipment.school_equipment.request.admin;

import lombok.Builder;

@Builder
public record RentalAddRequest(
        Long equipmentId,
        Long classroomId,
        int retCnt
) {
}
