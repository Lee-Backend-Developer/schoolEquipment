package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.admin.EquipmentEditRequest;
import com.equipment.school_equipment.request.equipment.EquipmentCount;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .content(request.equimentContent())
                .mainImg(request.image())
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

    public Page<Equipment> findAll(int pageNumber) {
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return equipmentRepository.findAll(pageRequest);
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void updateEquipment(EquipmentEditRequest request) {
        Category category = categoryRepository
                .findById(request.categoryId())
                .orElseThrow(IllegalArgumentException::new);

        Equipment equipment = equipmentRepository
                .equipmentAndCategory(request.id());

        equipment.editEquipment(request, category);
    }
}
