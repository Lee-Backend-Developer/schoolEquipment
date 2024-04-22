package com.equipment.school_equipment.request.classTime;


import lombok.Builder;

public record ClassTimeUpdate(
    String oldClassname,
    String newClassname,
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

    // builder 패턴
    public static ClassTimeUpdateBuilder builder() {
        return new ClassTimeUpdateBuilder();
    }

    public static class ClassTimeUpdateBuilder {
        private String oldClassname;
        private String newClassname;
        private boolean oneTime;
        private boolean twoTime;
        private boolean threeTime;
        private boolean fourTime;
        private boolean fiveTime;
        private boolean sixTime;
        private boolean sevenTime;
        private boolean eightTime;
        private boolean nineTime;
        private boolean tenTime;

        ClassTimeUpdateBuilder() {
        }

        public ClassTimeUpdateBuilder updateClassname(String oldClassname, String newClassname) {
            this.oldClassname = oldClassname;
            this.newClassname = newClassname;
            return this;
        }

        public ClassTimeUpdateBuilder oneTime(final boolean oneTime) {
            this.oneTime = oneTime;
            return this;
        }

        public ClassTimeUpdateBuilder twoTime(final boolean twoTime) {
            this.twoTime = twoTime;
            return this;
        }

        public ClassTimeUpdateBuilder threeTime(final boolean threeTime) {
            this.threeTime = threeTime;
            return this;
        }

        public ClassTimeUpdateBuilder fourTime(final boolean fourTime) {
            this.fourTime = fourTime;
            return this;
        }

        public ClassTimeUpdateBuilder fiveTime(final boolean fiveTime) {
            this.fiveTime = fiveTime;
            return this;
        }

        public ClassTimeUpdateBuilder sixTime(final boolean sixTime) {
            this.sixTime = sixTime;
            return this;
        }

        public ClassTimeUpdateBuilder sevenTime(final boolean sevenTime) {
            this.sevenTime = sevenTime;
            return this;
        }

        public ClassTimeUpdateBuilder eightTime(final boolean eightTime) {
            this.eightTime = eightTime;
            return this;
        }

        public ClassTimeUpdateBuilder nineTime(final boolean nineTime) {
            this.nineTime = nineTime;
            return this;
        }

        public ClassTimeUpdateBuilder tenTime(final boolean tenTime) {
            this.tenTime = tenTime;
            return this;
        }

        public ClassTimeUpdate build() {
            return new ClassTimeUpdate(this.oldClassname, this.newClassname, this.oneTime, this.twoTime, this.threeTime, this.fourTime, this.fiveTime, this.sixTime, this.sevenTime, this.eightTime, this.nineTime, this.tenTime);
        }

        public String toString() {
            return "ClassTimeUpdate.ClassTimeUpdateBuilder(oldClassname=" + this.oldClassname + ", newClassname=" + this.newClassname + ", oneTime=" + this.oneTime + ", twoTime=" + this.twoTime + ", threeTime=" + this.threeTime + ", fourTime=" + this.fourTime + ", fiveTime=" + this.fiveTime + ", sixTime=" + this.sixTime + ", sevenTime=" + this.sevenTime + ", eightTime=" + this.eightTime + ", nineTime=" + this.nineTime + ", tenTime=" + this.tenTime + ")";
        }
    }
}
