package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.EquipmentCategory;
import com.equipment.school_equipment.repository.EquipmentCategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
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
    public EquipmentCategory addEquipmentCategory(String name) {
        EquipmentCategory category = EquipmentCategory.builder().categoryName(name).build();
        return equipmentCategoryRepository.save(category);
    }


    public List<EquipmentCategory> findAll() {
            return equipmentCategoryRepository.findAll();
    }
}
