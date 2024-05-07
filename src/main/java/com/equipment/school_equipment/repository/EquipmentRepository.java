package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.custom.EquipmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentRepositoryCustom {
    Equipment findByName(String name);
}
