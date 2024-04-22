package com.equipment.school_equipment.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Rental {
    @Id
    @Column(name = "classtime_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;          //렌탈_아이디

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private final List<ClassTime> classTime = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private final List<Equipment> equipment = new ArrayList<>();

    private boolean rentalChk = true; //렌탈 여부

    public Rental(ClassTime classTime, Equipment equipment) {
        this.classTime.add(classTime);
        this.equipment.add(equipment);
    }
}
