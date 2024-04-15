package com.equipment.school_equipment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Equipment {
    @Id
    @Column(name = "equipment_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;      // 장비 아이디

    private String name;    // 장비 이름
    private int count;      // 장비 수량

    public Equipment(String name, int count) {
        this.name = name;
        this.count = count;
    }

    //비즈니스 로직
    public void setCount(int count) {
        this.count = this.count - count;
    }
}
