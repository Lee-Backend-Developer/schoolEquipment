package com.equipment.school_equipment.request.rental;

import lombok.Builder;

/**
 * 요청을 받는 반환 DTO
 * @param rentalId 렌탈_아이디
 * @param rentalCnt 반환할_수량
 */
@Builder
public record RentalReturn(
        Long rentalId, int rentalCnt
) {
}
