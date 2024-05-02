package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.equipment.school_equipment.domain.QCategory.*;
import static com.equipment.school_equipment.domain.QEquipment.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long countEquipment(Long categoryId) {
        Long fetch = queryFactory.select(equipment.count())
                .from(equipment).join(equipment.category, category)
                .where(equipment.category.id.eq(categoryId))
                .fetchOne();

        return fetch;
    }
}
