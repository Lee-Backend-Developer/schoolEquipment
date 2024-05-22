package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    Optional<Category> findByCategoryName(String name);
    Optional<Category> findByIdAndCategoryName(Long categoryId, String name);
}
