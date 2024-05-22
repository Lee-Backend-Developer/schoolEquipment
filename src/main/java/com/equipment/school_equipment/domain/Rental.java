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
    private Classes classes = new Classes();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipmentId = new Equipment();

    private int rentalCnt;      //빌린 갯수
    private boolean rentalChk = true; //렌탈 여부

    @Builder
    public Rental(Long id, Classes classes, Equipment equipmentId, int rentalCnt, boolean rentalChk) {
        this.id = id;
        this.classes = classes;
        this.equipmentId = equipmentId;
        this.rentalCnt = rentalCnt;
        this.rentalChk = rentalChk;
    }

    //비즈니스 로직
    public void updateRentalCnt(int rentalCnt) {
        this.rentalCnt = rentalCnt;
    }

    /**
     * 렌탈 여부 변경
     * @param rentalChk 대여사용 T, 반납F
     */
    public void updateRentalChk(boolean rentalChk) {
        this.rentalChk = rentalChk;
    }
}
