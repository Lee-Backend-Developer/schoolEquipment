package com.equipment.school_equipment.repository;

import com.equipment.school_equipment.domain.TodayRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayRentalRepository extends JpaRepository<TodayRental, Long> {
}
