package com.equipment.school_equipment.domain;


import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "classtimelist_id")
    private Classtimes classtimesId = new Classtimes();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipmentId = new Equipment();

    private int rentalCnt;      //빌린 갯수
    private boolean rentalChk = true; //렌탈 여부

    @Builder
    public Rental(Classtimes classtimesId, Equipment equipmentId, int rentalCnt, boolean rentalChk) {
        this.classtimesId = classtimesId;
        this.equipmentId = equipmentId;
        this.rentalCnt = rentalCnt;
        this.rentalChk = rentalChk;
    }

    //비즈니스 로직
    public void updateRentalCnt(int rentalCnt) {
        this.rentalCnt = rentalCnt;
    }
}
