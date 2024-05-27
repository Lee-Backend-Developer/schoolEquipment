package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentRepositoryCustom {
    Equipment equipmentAndCategory(Long id);
    Page<Equipment> findByCategory(String category, Pageable pageable);
}
