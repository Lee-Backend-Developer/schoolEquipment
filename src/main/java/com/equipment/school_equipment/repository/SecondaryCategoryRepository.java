package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.custom.SecondaryCategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecondaryCategoryRepository extends JpaRepository<SecondaryCategory, Long>, SecondaryCategoryRepositoryCustom {
    Optional<SecondaryCategory> findByCategoryName(String name);
    Optional<SecondaryCategory> findByIdAndCategoryName(Long categoryId, String name);

}
