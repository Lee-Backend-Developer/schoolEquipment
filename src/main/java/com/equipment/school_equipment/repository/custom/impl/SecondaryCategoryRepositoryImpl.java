package com.equipment.school_equipment.repository.custom.impl;


import com.equipment.school_equipment.domain.QSecondaryCategory;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.custom.SecondaryCategoryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.equipment.school_equipment.domain.QEquipment.equipment;
import static com.equipment.school_equipment.domain.QSecondaryCategory.secondaryCategory;

@RequiredArgsConstructor
public class SecondaryCategoryRepositoryImpl implements SecondaryCategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long countEquipment(Long categoryId) {
        Long fetch = queryFactory.select(equipment.count())
                .from(equipment).join(equipment.secondaryCategory, secondaryCategory)
                .where(equipment.secondaryCategory.id.eq(categoryId))
                .fetchOne();

        return fetch;
    }

    @Override
    public List<SecondaryCategory> findByPrimaryCategories(String primaryCategory) {
        return queryFactory.selectFrom(secondaryCategory)
                .where(secondaryCategory.primaryCategory.categoryName.like(primaryCategory))
                .fetch();
    }
}
