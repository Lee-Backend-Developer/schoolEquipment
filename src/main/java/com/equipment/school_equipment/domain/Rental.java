package com.equipment.school_equipment.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Rental {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;          //렌탈_아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_time_list_id")
    private ClassTimeList classTimeListId = new ClassTimeList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipmentId = new Equipment();

    private int rentalCnt;      //빌린 갯수
    private boolean rentalChk = true; //렌탈 여부

    @Builder
    public Rental(ClassTimeList classTimeListId, Equipment equipmentId, int rentalCnt, boolean rentalChk) {
        this.classTimeListId = classTimeListId;
        this.equipmentId = equipmentId;
        this.rentalCnt = rentalCnt;
        this.rentalChk = rentalChk;
    }
}
