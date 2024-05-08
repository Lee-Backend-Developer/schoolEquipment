package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.repository.custom.RentalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long>, RentalRepositoryCustom {
}
