package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.custom.SecondaryCategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrimaryCategoryRepository extends JpaRepository<PrimaryCategory, Long> {
    Optional<PrimaryCategory> findByCategoryName(String categoryName);
}
