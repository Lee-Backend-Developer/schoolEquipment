package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.QClasses;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.equipment.school_equipment.domain.QClasses.classes;
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
                .join(rental.classes, classes)
                .where(classes.classesId.eq(Long.parseLong(classNameId)).and(classes.dayOfWeek.eq(DayOfWeekEnum.valueOf(dayOfWeek))))
                .fetch();
    }

    @Override
    public Optional<Rental> findByClassIdAndEquipmentId(Long classId, Long equipmentId) {
        return Optional.ofNullable(queryFactory.selectFrom(rental)
                .where(rental.classes.classesId.eq(classId).and(rental.equipment.equipmentId.eq(equipmentId)))
                .fetchOne());
    }

    @Override
    public List<Rental> findAllAndRentalChkTrue() {
        return queryFactory.selectFrom(rental)
                .where(rental.rentalChk.isTrue())
                .fetch();
    }
}
