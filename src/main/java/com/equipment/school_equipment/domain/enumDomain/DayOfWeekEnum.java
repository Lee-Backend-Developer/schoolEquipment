package com.equipment.school_equipment.domain.enumDomain;

public enum DayOfWeekEnum{
    monday("월요일"),
    tuesday("화요일"),
    wednesday("수요일"),
    thursday("목요일"),
    friday("금요일");


    private final String week;

    DayOfWeekEnum(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }
}
