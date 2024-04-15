package com.equipment.school_equipment.service;


import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.request.equipment.EquipmentCount;
import com.equipment.school_equipment.request.equipment.EquipmentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Transactional
    public Equipment save(EquipmentCreate request) {
        Equipment saveEquipment = new Equipment(request.name(), request.count());
        return equipmentRepository.save(saveEquipment);
    }

    @Transactional
    public void countMinus(EquipmentCount request) {
        Equipment findEquipmentName = equipmentRepository.findByName(request.name());
        int resultCount = findEquipmentName.getCount() - request.count();
        findEquipmentName.setCount(resultCount);
    }

    @Transactional
    public void countPlus(EquipmentCount request) {
        Equipment findEquipmentName = equipmentRepository.findByName(request.name());
        int resultCount = findEquipmentName.getCount() + request.count();
        findEquipmentName.setCount(resultCount);

    }
}
