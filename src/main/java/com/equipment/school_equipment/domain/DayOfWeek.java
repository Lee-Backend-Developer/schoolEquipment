package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
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
public class DayOfWeek {

    @Id
    @Column(name = "DayOfWeek_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated
    private DayOfWeekEnum week;
}