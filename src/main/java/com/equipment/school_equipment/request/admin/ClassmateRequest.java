package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import static com.equipment.school_equipment.message.error.AdminErrorMessage.*;

@Builder
@Data
public class ClassmateRequest {
        private Long classnameId;

        @NotBlank(message = CLASSTIMES_ADD_ERROR)
        private String classname;
        @NotNull(message = CLASSTIMES_ADD_DAYOFWEEK_ERROR)
        private DayOfWeekEnum dayOfWeekEnum;

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
}