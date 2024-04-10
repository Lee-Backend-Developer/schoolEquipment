package com.equipment.school_equipment.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
public class Rental {
    @Id
    @Column(name = "classtime_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;          //렌탈_아이디

    @OneToMany(fetch = FetchType.LAZY)
    private List<ClassTime> classTime = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Equipment> equipment = new ArrayList<>();

}
