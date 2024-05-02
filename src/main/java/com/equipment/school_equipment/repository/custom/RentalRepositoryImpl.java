package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.QClasstimelist;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.equipment.school_equipment.domain.QClasstimelist.*;
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
                .join(rental.equipmentId, equipment).fetchJoin().fetchOne());

    }

    @Override
    public List<Rental> findRentals(String equipmentName) {
        return queryFactory.selectFrom(rental)
                .join(rental.equipmentId, equipment).fetchJoin().
                where(equipment.name.eq(equipmentName), rental.rentalChk.isTrue()).fetch();
    }

    @Override
    public List<Equipment> findByClassnameIdAndDayOfWeek(String classNameId, String dayOfWeek) {
        return queryFactory.select(rental.equipmentId)
                .from(rental)
                .join(rental.classtimelistId, classtimelist)
                .where(classtimelist.id.eq(Long.parseLong(classNameId)).and(classtimelist.dayOfWeek.eq(DayOfWeekEnum.valueOf(dayOfWeek))))
                .fetch();
    }
}
