package com.equipment.school_equipment.repository.custom.impl;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.custom.EquipmentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.equipment.school_equipment.domain.QEquipment.equipment;
import static com.equipment.school_equipment.domain.QSecondaryCategory.secondaryCategory;

@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Equipment equipmentAndCategory(Long id) {
        return queryFactory.selectFrom(equipment)
                .join(equipment.secondaryCategory, secondaryCategory)
                .where(equipment.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<Equipment> findByCategory(String category, Pageable pageable) {
        List<Equipment> fetch = queryFactory.selectFrom(equipment)
                .join(equipment.secondaryCategory)
                .where(equipment.secondaryCategory.primaryCategory.categoryName.eq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(equipment.count())
                .from(equipment)
                .where(equipment.secondaryCategory.categoryName.eq(category))
                .fetchOne();

        if(Objects.isNull(total)) total = 0L;

        return new PageImpl<>(fetch, pageable, total);

    }
}
