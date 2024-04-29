package com.equipment.school_equipment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EquipmentCategory {
    @Id
    @Column(name = "equipment_category_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String categoryName;

    @OneToMany(mappedBy = "id")
    List<Equipment> equipmentList = new ArrayList<>();

    @Builder
    public EquipmentCategory(String categoryName,Equipment equipment) {
        this.categoryName = categoryName;
        this.equipmentList.add(equipment);
    }
}
