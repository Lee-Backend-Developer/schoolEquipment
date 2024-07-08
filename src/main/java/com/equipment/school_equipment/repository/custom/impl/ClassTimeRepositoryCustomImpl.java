package com.equipment.school_equipment.repository.custom.impl;

import com.equipment.school_equipment.domain.ClassPeriod;
import com.equipment.school_equipment.repository.custom.ClassTimeRepositoryCustom;
import com.equipment.school_equipment.request.admin.ClassPeriodPageCondition;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.equipment.school_equipment.domain.QClassPeriod.classPeriod;

@RequiredArgsConstructor
public class ClassTimeRepositoryCustomImpl implements ClassTimeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ClassPeriod> classPeriodPage(ClassPeriodPageCondition condition, Pageable pageable) {
        if(Objects.nonNull(condition.getWeek())){
            List<ClassPeriod> queryClassPeriod = jpaQueryFactory.selectFrom(classPeriod)
                    .where(classPeriod.dayOfWeek.eq(condition.getWeek()))
                    .offset(condition.getPage()) // page number
                    .limit(10) // page size
                    .fetch();

            Long total = jpaQueryFactory.select(classPeriod.count()).from(classPeriod)
                    .where(classPeriod.dayOfWeek.eq(condition.getWeek()))
                    .offset(condition.getPage()) // page number
                    .limit(10) // page size
                    .fetchOne();
            return new PageImpl<>(queryClassPeriod, pageable, total);
        }
        JPAQuery<ClassPeriod> classPeriodJPAQuery = jpaQueryFactory.selectFrom(classPeriod)
                .offset(condition.getPage()) // page number
                .limit(pageable.getPageSize());// page size

        Long total = jpaQueryFactory.select(classPeriod.count())
                .from(classPeriod).fetchOne();
        return new PageImpl<>(classPeriodJPAQuery.fetch(), pageable, total);

    }

}
