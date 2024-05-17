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

    /**
     * 현재 장비 남은 수량 확인
     *
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

    /**
     * 대여를 추가 할 때 검증
     * 1. 수업 요일에 장비가 있는지 확인
     * INPUT: 수업명, 요일를 가지고 대여 테이블에 조회함 만약에
     * 있다면
     * 1.1 렌탈 장비 + 사용자가 렌탈 할 수
     * 없다면
     * 1.2 새로 추가함
     *
     * @param request 사용자로
     */
    @Transactional
    public void rentalCreate(RentalAddRequest request) {
        Classtimes classtime = classTimeRepository.findById(request.getClassroomId()).orElseThrow(IllegalArgumentException::new);
        Equipment equipment = equipmentRepository.findById(request.getEquipmentId()).orElseThrow(IllegalArgumentException::new);

        // 보관하고 있는 장비 수량보다 대여 수량이 더 많으면 에러
        if(equipment.getCount() < request.getRetCnt()) throw new IllegalArgumentException("보관하고 있는 장비 수량보다 대여 수량이 더 많습니다.");

        // 대여한 장비와 수업 시간이 똑같을 경우 처리하는 로직
        rentalRepository.findByClassIdAndEquipmentId(classtime.getId(), equipment.getId())
                .ifPresentOrElse(rental1 ->  // 있다면
                            rental1.updateRentalCnt(rental1.getRentalCnt() + request.getRetCnt()),
                        () -> { // 없다면
                            Rental rental = Rental.builder()
                                    .equipmentId(equipment).classtimesId(classtime)
                                    .rentalCnt(request.getRetCnt()).rentalChk(true).build();
                            rentalRepository.save(rental);
                        }
                );
    }

    public List<Rental> findByAll() {
        return rentalRepository.findAllAndRentalChkTrue();
    }

    public Equipment findByEquipment(String classname, String day) {
        Classtimes classTime = classTimeRepository.findByClassNameEqualsAndDayOfWeek(classname, DayOfWeekEnum.valueOf(day))
                .orElseThrow(() -> new RuntimeException("그런 수업은 없습니다."));
        Rental rental = rentalRepository.findByClassOfDay(classTime.getClassName(), day).orElseThrow(() -> new RuntimeException("수업 또는 요일이 잘못입력되었습니다"));
        return rental.getEquipmentId();
    }

    /**
     * 남은수 = (가지고 있던 수량 - 렌탈 장비 수)
     *
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

    private static int valedationLeftEquipment(RentalCreate request, Equipment equipment) {
        int verification = equipment.getCount() - request.equipmentCount();
        if (verification < 0) throw new IllegalArgumentException();
        return verification;
    }

    @Transactional
    public void rentaldelete(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 아이디가 없습니다."));
        rentalRepository.deleteById(rental.getId());
    }
}
