package com.equipment.school_equipment.repository.dto;

import lombok.Builder;

public record TodayRentalSelectDto(
    Long id,            // id
    String name,        // 이름
    String mainImg,     // 제품 이미지 경로
    int equipmentCnt,   // 제품 보유량
    Integer rentalInt  // 제품 대여 숫자
){
    public TodayRentalSelectDto(Long id, String name, String mainImg, int equipmentCnt, Integer rentalInt) {
        this.id = id;
        this.name = name;
        this.mainImg = mainImg;
        this.equipmentCnt = equipmentCnt;
        this.rentalInt = rentalInt;
    }
}