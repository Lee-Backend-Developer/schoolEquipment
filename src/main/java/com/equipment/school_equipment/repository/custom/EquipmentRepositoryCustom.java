package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EquipmentRepositoryCustom {
    Equipment equipmentAndCategory(Long id);
    Page<Equipment> findByCategory(String category, Pageable pageable);
    List<Equipment> findByEquipmentAndPrimaryCategoryAndSecondaryCategory(Long primaryCategoryId, Long secondaryCategoryId);
}
