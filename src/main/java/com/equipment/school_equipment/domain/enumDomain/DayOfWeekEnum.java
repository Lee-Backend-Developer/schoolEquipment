package com.equipment.school_equipment.domain.enumDomain;

public enum DayOfWeekEnum{
    Monday("월요일"),
    Tuesday("화요일"),
    Wednesday("수요일"),
    Thursday("목요일"),
    Friday("금요일");


    private final String week;

    DayOfWeekEnum(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }
}
