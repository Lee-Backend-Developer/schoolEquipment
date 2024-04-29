package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.EquipmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategory, Long> {
    Optional<EquipmentCategory> findByCategoryName(String name);
}
