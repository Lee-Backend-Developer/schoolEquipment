package com.equipment.school_equipment.service;

import com.equipment.school_equipment.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final EquipmentRepository equipmentRepository;
}
