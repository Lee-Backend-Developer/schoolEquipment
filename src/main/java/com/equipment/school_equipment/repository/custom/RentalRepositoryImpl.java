package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Rental;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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
}
