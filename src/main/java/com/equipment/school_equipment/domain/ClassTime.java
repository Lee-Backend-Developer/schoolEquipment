package com.equipment.school_equipment.domain;

import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClassTime {
    @Id
    @Column(name = "classtime_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;          //수업아이디

    private String className;   //수업이름

    @Enumerated(EnumType.STRING)
    private DayOfWeekEnum dayOfWeek;

    private boolean oneTime;    //1교시 (9:00 ~ 9:50)
    private boolean twoTime;    //2교시 (10:00 ~ 10:50)
    private boolean threeTime;  //3교시 (11:00 ~ 11:50)
    private boolean fourTime;   //4교시 (12:00 ~ 12:50)
    private boolean fiveTime;   //5교시 (13:00 ~ 13:50)
    private boolean sixTime;    //6교시 (14:00 ~ 14:50)
    private boolean sevenTime;  //7교시 (15:00 ~ 15:50)
    private boolean eightTime;  //8교시 (16:00 ~ 16:50)
    private boolean nineTime;   //9교시 (17:00 ~ 17:50)
    private boolean tenTime;    //10교시(18:00 ~ 18:50)

    @Builder
    public ClassTime(String className, DayOfWeekEnum dayOfWeek, boolean oneTime, boolean twoTime, boolean threeTime, boolean fourTime, boolean fiveTime, boolean sixTime, boolean sevenTime, boolean eightTime, boolean nineTime, boolean tenTime) {
        this.className = className;
        this.dayOfWeek = dayOfWeek;
        this.oneTime = oneTime;
        this.twoTime = twoTime;
        this.threeTime = threeTime;
        this.fourTime = fourTime;
        this.fiveTime = fiveTime;
        this.sixTime = sixTime;
        this.sevenTime = sevenTime;
        this.eightTime = eightTime;
        this.nineTime = nineTime;
        this.tenTime = tenTime;
    }

    // 비즈니스 로직
    public void setUpdate(String newClassname, boolean oneTime, boolean twoTime, boolean threeTime, boolean fourTime, boolean fiveTime, boolean sixTime, boolean sevenTime, boolean eightTime, boolean nineTime, boolean tenTime) {
        this.className = newClassname;
        this.oneTime = oneTime;
        this.twoTime = twoTime;
        this.threeTime = threeTime;
        this.fourTime = fourTime;
        this.fiveTime = fiveTime;
        this.sixTime = sixTime;
        this.sevenTime = sevenTime;
        this.eightTime = eightTime;
        this.nineTime = nineTime;
        this.tenTime = tenTime;
    }

}


