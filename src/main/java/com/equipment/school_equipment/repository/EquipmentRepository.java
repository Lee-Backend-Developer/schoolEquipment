package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByName(String name);
}
