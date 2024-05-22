package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.equipment.school_equipment.domain.QCategory.category;
import static com.equipment.school_equipment.domain.QEquipment.equipment;

@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Equipment equipmentAndCategory(Long id) {
        return queryFactory.selectFrom(equipment)
                .join(equipment.category, category)
                .where(equipment.equipmentId.eq(id))
                .fetchOne();
    }
}
