package com.equipment.school_equipment.repository.dto;


public record TodayRentalSelect (
    Long id,
    String name,
    String mainImg,
    int rentalCnt,
    int equipmentCnt
){}