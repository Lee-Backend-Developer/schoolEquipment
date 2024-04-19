package com.equipment.school_equipment.request.classTime;

import lombok.Builder;

public record ClassTimeCreate(
        String className,
        boolean oneTime,
        boolean twoTime,
        boolean threeTime,
        boolean fourTime,
        boolean fiveTime,
        boolean sixTime,
        boolean sevenTime,
        boolean eightTime,
        boolean nineTime,
        boolean tenTime
) {
    @Builder
    public ClassTimeCreate(String className, boolean oneTime, boolean twoTime, boolean threeTime, boolean fourTime, boolean fiveTime, boolean sixTime, boolean sevenTime, boolean eightTime, boolean nineTime, boolean tenTime) {
        this.className = className;
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
