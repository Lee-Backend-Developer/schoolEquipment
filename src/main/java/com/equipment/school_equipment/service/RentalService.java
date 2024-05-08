package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.repository.ClassTimeRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.RentalRepository;
import com.equipment.school_equipment.request.admin.RentalAddRequest;
import com.equipment.school_equipment.request.rental.RentalCreate;
import com.equipment.school_equipment.request.rental.RentalReturn;
import com.equipment.school_equipment.repository.dto.RentalDuplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalService {

    private final EquipmentRepository equipmentRepository;
    private final ClassTimeRepository classTimeRepository;
    private final RentalRepository rentalRepository;

    /**
     * 현재 장비 남은 수량 확인
     * @param request 수업명, 요일, 장비, 장비빌릴수
     * @return 장비남은수량
     * @throws RuntimeException 요일 or 요청 받은 장비가 남은 장비보다 넘칠때 오류
     */
    @Transactional
    public int rentalCreate(RentalCreate request) {

        Classtimes classTimeList = classTimeRepository.findByClassName(request.className()).orElseThrow(IllegalArgumentException::new);
        Equipment equipment = equipmentRepository.findByName(request.equipmentName());

        Rental rental = Rental.builder().classtimesId(classTimeList)
                .equipmentId(equipment)
                .rentalCnt(request.equipmentCount())
                .build();
        //가지고 있는 장비수, 요청받은 입력수 검증로직이 필요
        int verification = valedationLeftEquipment(request, equipment);

        rentalRepository.save(rental);

        return verification;
    }

    @Transactional
    public int rentalCreate(RentalAddRequest request){
        Classtimes classtime = classTimeRepository.findById(request.classroomId()).orElseThrow(IllegalArgumentException::new);
        Equipment equipment = equipmentRepository.findById(request.equipmentId()).orElseThrow(IllegalArgumentException::new);
        Rental rental = Rental.builder().equipmentId(equipment).classtimesId(classtime).rentalCnt(request.retCnt()).rentalChk(true).build();
        rentalRepository.save(rental);
        return 0;
    }

    @Transactional
    public void rentalReturn(RentalReturn request) {
        Rental findRental = rentalRepository.findById(request.rentalId()).orElseThrow(() -> new RuntimeException("렌탈한 이력이 없습니다."));
        if(findRental.getRentalCnt() < request.rentalCnt()) {
            throw new RuntimeException("반환한 횟수보다 입력하신 횟수가 큽니다.");
        }
        findRental.updateRentalCnt(findRental.getRentalCnt() - request.rentalCnt());
    }

    public List<Rental> findByAll() {
        return rentalRepository.findAll();
    }

    public Equipment findByEquipment(String classname, String day) {
        Classtimes classTime = classTimeRepository.findByClassNameEqualsAndDayOfWeek(classname, DayOfWeekEnum.valueOf(day))
                .orElseThrow(() -> new RuntimeException("그런 수업은 없습니다."));
        Rental rental = rentalRepository.findByClassOfDay(classTime.getClassName(), day).orElseThrow(() -> new RuntimeException("수업 또는 요일이 잘못입력되었습니다"));
        return rental.getEquipmentId();
    }

    /**
     * 남은수 = (가지고 있던 수량 - 렌탈 장비 수)
     * @param equipmentName 장비 이름 받아야됨
     * @return 남은갯수 반환
     */
    public int findByEquipmentCnt(String equipmentName) {
        List<Rental> findRental = rentalRepository.findRentals(equipmentName);
        int count = equipmentRepository.findByName(equipmentName).getCount();
        for (Rental rentalsObj : findRental) count -= rentalsObj.getRentalCnt();

        return count;
    }

    public List<Equipment> findByClassnameIdAndDayOfWeek(String classNameId, String dayOfWeek) {
        List<Equipment> equipments = rentalRepository.findByClassnameIdAndDayOfWeek(classNameId, dayOfWeek);

        return equipments;
    }

    public List<Rental> findByDuplication() {
        List<RentalDuplication> duplications = rentalRepository.findByDuplication();
        return duplications.stream().map(rentalDuplication -> Rental.builder()
                .equipmentId(rentalDuplication.equipment())
                .classtimesId(rentalDuplication.classtimes())
                .rentalCnt(rentalDuplication.sumCnt())
                .build()).toList();
    }

    private static int valedationLeftEquipment(RentalCreate request, Equipment equipment) {
        int verification = equipment.getCount() - request.equipmentCount();
        if(verification < 0) throw new IllegalArgumentException();
        return verification;
    }
}
