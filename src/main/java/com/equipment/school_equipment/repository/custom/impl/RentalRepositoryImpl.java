package com.equipment.school_equipment.repository.custom.impl;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.custom.RentalRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.equipment.school_equipment.domain.QClassPeriod.*;
import static com.equipment.school_equipment.domain.QEquipment.equipment;
import static com.equipment.school_equipment.domain.QRental.rental;

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
    public List<Rental> findAllAndRentalChkTrue() {
        return queryFactory.selectFrom(rental)
                .where(rental.rentalChk.isTrue())
                .fetch();
    }

    @Override
    public List<Rental> findByWeekday(DayOfWeekEnum weekday) {
        return queryFactory.selectFrom(rental)
                .join(rental.classPeriod)
                .fetchJoin()
                .where(rental.classPeriod.dayOfWeek.eq(weekday))
                .fetch();
    }
}
