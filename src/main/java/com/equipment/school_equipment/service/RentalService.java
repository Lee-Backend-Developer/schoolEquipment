package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.ClassTimeList;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.RentalRepository;
import com.equipment.school_equipment.request.rental.RentalCreate;
import com.equipment.school_equipment.request.rental.RentalReturn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalService {

    private final EquipmentRepository equipmentRepository;
    private final ClassTimeRepository classTimeRepository;
    private final RentalRepository rentalRepository;

    @Transactional
    public Rental rentalCreate(RentalCreate request) throws RuntimeException {

        Equipment findEquipment = equipmentRepository.findByName(request.equipmentName());

        //장비 수량 검증
        if (findEquipment.getCount() < request.equipmentCount()) {
            throw new RuntimeException("수량초과");
        } else if (findEquipment.getCount() == request.equipmentCount()) {
            //현재 남은 수량과 사용자가 입력한 수량이 같을때
        }
        findEquipment.editCount(findEquipment.getCount() - request.equipmentCount());

        ClassTimeList findClasstime = classTimeRepository.findByClassName(request.className()).orElseThrow(() -> new RuntimeException("수업명이 존재하지 않음"));

        Rental rental = Rental.builder().classTimeListId(findClasstime).equipmentId(findEquipment).build();
        return rentalRepository.save(rental);
    }


    @Transactional
    public void rentalReturn(RentalReturn request) {
        Equipment findNameEquipment = equipmentRepository.findByName(request.equipmentName());
        findNameEquipment.editCount(findNameEquipment.getCount() + request.retCount());
    }

    public List<Equipment> findByEquipment(String classname, String day) {
        ClassTimeList classTime = classTimeRepository.findByClassNameEqualsAndDayOfWeek(classname, DayOfWeekEnum.valueOf(day))
                .orElseThrow(() -> new RuntimeException("그런 수업은 없습니다."));
        Rental rental = rentalRepository.findByClassOfDay(classname, day).orElseThrow(() -> new RuntimeException("수업 또는 요일이 잘못입력되었습니다"));
        return null;
    }
}
