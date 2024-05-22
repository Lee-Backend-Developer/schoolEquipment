package com.equipment.school_equipment.repository.custom;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.equipment.school_equipment.domain.QCategory.category;
import static com.equipment.school_equipment.domain.QEquipment.equipment;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long countEquipment(Long categoryId) {
        Long fetch = queryFactory.select(equipment.count())
                .from(equipment).join(equipment.category, category)
                .where(equipment.category.categoryId.eq(categoryId))
                .fetchOne();

        return fetch;
    }
}
