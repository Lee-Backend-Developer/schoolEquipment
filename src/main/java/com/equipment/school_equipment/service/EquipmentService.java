package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.equipment.EquipmentCount;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Equipment save(EquipmentCreate request) {
        Category findCategory = categoryRepository.findById(request.categoryId()).orElseThrow(IllegalArgumentException::new);
        Equipment saveEquipment = Equipment.builder()
                .name(request.name())
                .count(request.count())
                .build();
        saveEquipment.addCategory(findCategory);
        return equipmentRepository.save(saveEquipment);
    }

    @Transactional
    public void countMinus(EquipmentCount request) {
        Equipment findEquipmentName = equipmentRepository.findByName(request.name());
        int resultCount = findEquipmentName.getCount() - request.count();
        findEquipmentName.editCount(resultCount);
    }

    @Transactional
    public void countPlus(EquipmentCount request) {
        Equipment findEquipmentName = equipmentRepository.findByName(request.name());
        int resultCount = findEquipmentName.getCount() + request.count();
        findEquipmentName.editCount(resultCount);

    }

    @Transactional
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> findAll() {
            return equipmentRepository.findAll();
    }
}
