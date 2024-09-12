package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.request.admin.ClassPeriodPageCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassTimeRepositoryCustom {

    Page<ClassPeriod> classPeriodPage(ClassPeriodPageCondition condition, Pageable pageable);
}
