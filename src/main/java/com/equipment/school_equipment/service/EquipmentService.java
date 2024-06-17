package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.SecondaryCategoryRepository;
import com.equipment.school_equipment.request.admin.EquipmentForm;
import com.equipment.school_equipment.request.equipment.EquipmentCount;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import com.equipment.school_equipment.util.FileSaveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final SecondaryCategoryRepository secondaryCategoryRepository;

    @Transactional
    public Equipment save(EquipmentCreate request) {
        SecondaryCategory findSecondaryCategory = secondaryCategoryRepository.findById(request.categoryId()).orElseThrow(IllegalArgumentException::new);
        Equipment saveEquipment = Equipment.builder()
                .name(request.name())
                .count(request.count())
                .content(request.equimentContent())
                .mainImg(request.image())
                .build();
        saveEquipment.addCategory(findSecondaryCategory);
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

    public Page<Equipment> findAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return equipmentRepository.findAll(pageRequest);
    }

    public Page<Equipment> findByCategoryId(String category, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);

        if (category.isEmpty()) {
            return equipmentRepository.findAll(pageRequest);
        }
        return equipmentRepository.findByCategory(category, pageRequest);
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void updateEquipment(EquipmentForm request) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository
                .findById(request.getSecondaryCategory().getId())
                .orElseThrow(IllegalArgumentException::new);

        Equipment equipment = equipmentRepository
                .equipmentAndCategory(request.getEquipmentId());

        if(request.getImage() != null) {
            String fileName = FileSaveUtil.fileSave(request.getImage(), FileSaveUtil.PATH_EQUIPMENT);
            request.setImageName(fileName);
        }

        equipment.editEquipment(request, secondaryCategory);
    }

    public List<Equipment> findByEquipmentAndPrimaryCategoryAndSecondaryCategory(Long primaryCategoryId, Long secondaryCategoryId) {
        Objects.requireNonNull(primaryCategoryId);
        Objects.requireNonNull(secondaryCategoryId);
        List<Equipment> equipmentList = equipmentRepository.findByEquipmentAndPrimaryCategoryAndSecondaryCategory(primaryCategoryId, secondaryCategoryId);
        if (equipmentList.isEmpty()) throw new NullPointerException("없습니다");
        return equipmentList;
    }
}
