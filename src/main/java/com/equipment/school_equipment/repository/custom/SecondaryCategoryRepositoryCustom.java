package com.equipment.school_equipment.repository.custom;

import com.equipment.school_equipment.domain.SecondaryCategory;

import java.util.List;

public interface SecondaryCategoryRepositoryCustom {
    Long countEquipment(Long categoryId);
    List<SecondaryCategory> findByPrimaryCategories(String primaryCategory);

}
