package com.equipment.school_equipment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PrimaryCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoryName;

    @Builder
    public PrimaryCategory(long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    // 비즈니스 로직
    public void updateChangeName(String changeName){
        this.categoryName = changeName;
    }
}
