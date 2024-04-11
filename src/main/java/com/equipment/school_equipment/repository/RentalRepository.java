package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
