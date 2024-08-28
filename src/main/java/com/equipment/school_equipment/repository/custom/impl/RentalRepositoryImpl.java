package com.equipment.school_equipment.repository.custom.impl;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.custom.RentalRepositoryCustom;
import com.equipment.school_equipment.repository.dto.TodayRentalSelectDto;
import com.equipment.school_equipment.request.admin.RentalPageCondition;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.equipment.school_equipment.domain.QClassPeriod.*;
import static com.equipment.school_equipment.domain.QEquipment.equipment;
import static com.equipment.school_equipment.domain.QRental.rental;
import static com.equipment.school_equipment.domain.QTodayRental.*;

@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * @param className  수업명
     * @param classOfDay 수업요일
     * @return Rental 반환
     */
    @Override
    public Optional<Rental> findByClassOfDay(String className, String classOfDay) {

        return Optional.ofNullable(queryFactory.select(rental)
                .from(rental)
                .join(rental.equipment, equipment).fetchJoin().fetchOne());

    }

    @Override
    public List<Rental> findRentals(String equipmentName) {
        return queryFactory.selectFrom(rental)
                .join(rental.equipment, equipment).fetchJoin().
                where(equipment.name.eq(equipmentName), rental.rentalChk.isTrue()).fetch();
    }

    @Override
    public List<Equipment> findByClassnameIdAndDayOfWeek(String classNameId, String dayOfWeek) {
        return queryFactory.select(rental.equipment)
                .from(rental)
                .join(rental.classPeriod, classPeriod)
                .where(classPeriod.id.eq(Long.parseLong(classNameId)).and(classPeriod.dayOfWeek.eq(DayOfWeekEnum.valueOf(dayOfWeek))))
                .fetch();
    }

    @Override
    public Optional<Rental> findByClassIdAndEquipmentId(Long classId, Long equipmentId) {
        return Optional.ofNullable(queryFactory.selectFrom(rental)
                .where(rental.classPeriod.id.eq(classId).and(rental.equipment.id.eq(equipmentId)))
                .fetchOne());
    }

    @Override
    public Page<Rental> findAllAndRentalChkTruePage(RentalPageCondition condition, Pageable pageable) {
        JPAQuery<Rental> rentalJPAQuery;
        long total;

        // 요일이 없을 경우
        if (Objects.isNull(condition.getWeek())) {
            rentalJPAQuery = queryFactory.selectFrom(rental)
                    .where(rental.rentalChk.isTrue())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize());

            total = totalAllCntRet(rental);
        } else { // 요일이 있을경우
            rentalJPAQuery = queryFactory.selectFrom(rental)
                    .where(rental.rentalChk.isTrue().and(rental.classPeriod.dayOfWeek.eq(condition.getWeek())))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize());

            total = queryFactory.select(rental.count()).from(rental)
                    .where(rental.rentalChk.isTrue().and(rental.classPeriod.dayOfWeek.eq(condition.getWeek()))).fetchOne();
        }

        return new PageImpl<>(rentalJPAQuery.fetch(), pageable, total);
    }

    @Override
    public List<Rental> findByWeekday(DayOfWeekEnum weekday) {
        return queryFactory.selectFrom(rental)
                .join(rental.classPeriod)
                .fetchJoin()
                .where(rental.classPeriod.dayOfWeek.eq(weekday))
                .fetch();
    }

    private Long totalAllCntRet(EntityPathBase qClass) {
        Long cnt = (Long) queryFactory.select(qClass.count()).from(qClass).fetchOne();
        return cnt;
    }

    @Override
    public Page<Rental> findAllAndRentalCategoryPage(RentalPageCondition condition, Pageable pageable) {
        List<Rental> rentalList = queryFactory.selectFrom(rental)
                .where(rental.classPeriod.className.eq(condition.getCategory()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long tatal = queryFactory.select(rental.count()).from(rental)
                .where(rental.classPeriod.className.eq(condition.getCategory()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();

        return new PageImpl<>(rentalList, pageable, tatal);
    }

    @Override
    public Page<TodayRentalSelectDto> findRentalJoinTodayRental(Pageable pageable) {

        List<TodayRentalSelectDto> fetch = queryFactory
                .select(Projections.constructor(TodayRentalSelectDto.class,
                        equipment.id,
                        equipment.name,
                        equipment.mainImg,
                        equipment.count,
                        todayRental.rentalMinusCount))
                .from(equipment)
                .leftJoin(rental).on(equipment.eq(rental.equipment))
                .leftJoin(todayRental).on(todayRental.rental.eq(rental))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long tatalCnt = queryFactory
                .select(equipment.count())
                .from(equipment)
                .fetchOne();


        return new PageImpl<>(fetch, pageable, tatalCnt);
    }

    @Override
    public Page<TodayRentalSelectDto> findCategoryAndEquipment(String category, Pageable pageable) {
        List<TodayRentalSelectDto> fetch = queryFactory
                .select(Projections.constructor(TodayRentalSelectDto.class,
                        equipment.id,
                        equipment.name,
                        equipment.mainImg,
                        equipment.count,
                        todayRental.rentalMinusCount))
                .from(equipment)
                .leftJoin(rental).on(equipment.eq(rental.equipment))
                .leftJoin(todayRental).on(todayRental.rental.eq(rental))
                .where(equipment.secondaryCategory.primaryCategory.categoryName.eq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long tatalCnt = queryFactory
                .select(equipment.count())
                .from(equipment)
                .where(equipment.secondaryCategory.primaryCategory.categoryName.eq(category))
                .fetchOne();

        return new PageImpl<>(fetch, pageable, tatalCnt);
    }
}
