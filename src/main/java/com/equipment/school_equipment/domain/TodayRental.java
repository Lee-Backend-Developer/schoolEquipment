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
public class TodayRental {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rental rental;

    private int rentalMinusCount;

    @Builder
    public TodayRental(Rental rental, int rentalMinusCount) {
        this.rental = rental;
        this.rentalMinusCount = rentalMinusCount;
    }
}
