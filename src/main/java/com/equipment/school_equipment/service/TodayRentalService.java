package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.TodayRental;
import com.equipment.school_equipment.domain.classPeriod.DayOfWeekEnum;
import com.equipment.school_equipment.repository.RentalRepository;
import com.equipment.school_equipment.repository.TodayRentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodayRentalService {

    private final TodayRentalRepository todayRentalRepository;
    private final RentalRepository rentalRepository;

    @Transactional
    public void createRentalFromToday(DayOfWeekEnum weekday) {
        todayRentalRepository.deleteAll();
        List<Rental> rentalList = rentalRepository.findByWeekday(weekday);
        try {
            if(rentalList.isEmpty())
                throw new NullPointerException();
        } catch (NullPointerException e) {
            log.error("대여된 장비가 없습니다.");
        }
        rentalList.forEach(rental -> todayRentalRepository.save(TodayRental.builder()
                .rentalMinusCount(rental.getRentalCnt())
                .rental(rental)
                .build()));
    }
}
