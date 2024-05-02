package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentCategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);
    Optional<Category> findByIdAndCategoryName(Long id, String name);
}
