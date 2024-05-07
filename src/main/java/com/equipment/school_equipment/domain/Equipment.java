package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.request.admin.EquipmentAddRequest;
import com.equipment.school_equipment.request.admin.EquipmentEditRequest;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    private String mainImg;
    private String content;
    private int count;      // 장비 수량

    @Builder
    public Equipment(String name, String mainImg, String content, int count) {
        this.name = name;
        this.mainImg = mainImg;
        this.content = content;
        this.count = count;
    }

    //비즈니스 로직
    public void editCount(int count) {
        this.count = count;
    }

    public void editEquipment(EquipmentEditRequest equipment, Category category) {
        this.name = equipment.name();
        this.mainImg = equipment.mainImg();
        this.content = equipment.content();
        this.count = equipment.count();
        this.category = category;
    }

    public void addCategory(Category category) {
        this.category = category;
        category.getEquipmentList().add(this);
    }
}
