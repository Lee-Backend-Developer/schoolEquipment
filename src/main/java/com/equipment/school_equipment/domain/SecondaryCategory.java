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
public class SecondaryCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Equipment> equipmentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private PrimaryCategory primaryCategory;

    @Builder
    public SecondaryCategory(String categoryName, PrimaryCategory primaryCategory) {
        this.categoryName = categoryName;
        this.primaryCategory = primaryCategory;
    }

    // 비즈니스 로직
    public void updateName(String changeName) {
        this.categoryName = changeName;
    }
}
