package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.repository.EquipmentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;

    @Transactional
    public Category addEquipmentCategory(String name) {
        Category category = Category.builder().categoryName(name).build();
        return equipmentCategoryRepository.save(category);
    }


    public List<Category> findAll() {
            return equipmentCategoryRepository.findAll();
    }
}
