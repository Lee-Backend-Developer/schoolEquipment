package com.equipment.school_equipment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;

import static jakarta.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
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
}
