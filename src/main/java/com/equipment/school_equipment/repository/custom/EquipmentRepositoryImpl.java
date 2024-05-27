package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.equipment.school_equipment.domain.QCategory.category;
import static com.equipment.school_equipment.domain.QEquipment.equipment;

@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Equipment equipmentAndCategory(Long id) {
        return queryFactory.selectFrom(equipment)
                .join(equipment.category, category)
                .where(equipment.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<Equipment> findByCategory(String category, Pageable pageable) {
        List<Equipment> fetch = queryFactory.selectFrom(equipment)
                .join(equipment.category)
                .where(equipment.category.categoryName.eq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(equipment.count())
                .from(equipment)
                .where(equipment.category.categoryName.eq(category))
                .fetchOne();

        if(Objects.isNull(total)) total = 0L;

        return new PageImpl<>(fetch, pageable, total);

    }
}
