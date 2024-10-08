package com.equipment.school_equipment.domain.classPeriod;

import java.util.Arrays;

public enum DayOfWeekEnum{
    monday("월요일"),
    tuesday("화요일"),
    wednesday("수요일"),
    thursday("목요일"),
    friday("금요일");
    // week => monday
    // name => "월요일"

    // error Message
    public static final String DAYOFWEEK_ERROR = "요일이 선택되지 않음.";

    private final String week;

    DayOfWeekEnum(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    /**
     * 요일을 받아서 해당 요일을 반환
     * @param week 요일
     * @return DayOfWeekEnum
     */
    public static DayOfWeekEnum getName(String week) {
        return Arrays.stream(DayOfWeekEnum.values())
                .filter(values -> values.getWeek().equals(week))
                .findFirst().orElseThrow(NullPointerException::new);
    }
}
