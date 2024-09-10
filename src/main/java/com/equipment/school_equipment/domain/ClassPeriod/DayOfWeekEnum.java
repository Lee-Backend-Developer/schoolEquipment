package com.equipment.school_equipment.domain.enumDomain;

import java.util.Arrays;

public enum DayOfWeekEnum{
    monday("월요일"),
    tuesday("화요일"),
    wednesday("수요일"),
    thursday("목요일"),
    friday("금요일");
    // week => monday
    // name => "월요일"


    private final String week;

    DayOfWeekEnum(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    public static DayOfWeekEnum getName(String week) {
        return Arrays.stream(DayOfWeekEnum.values())
                .filter(values -> values.getWeek().equals(week))
                .findFirst().orElseThrow(NullPointerException::new);
    }
}
