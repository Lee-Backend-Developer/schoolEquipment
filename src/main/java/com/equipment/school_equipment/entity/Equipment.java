package com.equipment.school_equipment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Equipment {
    @Id
    private double id;      // 장비 아이디
    @Column
    private String name;    // 장비 이름
    @Column
    private int count;      // 장비 수량

    public Equipment(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
