package com.equipment.school_equipment.request.admin;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.domain.ClassPeriod;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.equipment.school_equipment.message.error.AdminErrorMessage.*;

@Builder
@Data
public class RentalAddRequest {

        @NotNull(message = RENTAL_ADD_EQUIPMENT_ERROR)
        private Long equipmentId;
        @NotNull(message = RENTAL_ADD_WEEKDAY_ERROR)
        private DayOfWeekEnum weekday;
        @NotNull(message = RENTAL_ADD_CLASSROOM_ERROR)
        private Long classroomId;
        @Min(value = 1, message = RENTAL_ADD_COUNT_ERROR)
        private int retCnt;
        @NotNull
        private PrimaryCategory primaryCategory;
        @NotNull
        private SecondaryCategory secondaryCategory;

        private Equipment equipment;
        private ClassPeriod classPeriod;

        //model
        private List<PrimaryCategory> primaryCategoryList;
        private List<SecondaryCategory> secondaryCategoryList;

        //response
        private List<Equipment> equipmentList;
        private List<ClassPeriod> classtimeList;
}
