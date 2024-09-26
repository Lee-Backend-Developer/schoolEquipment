package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.domain.Rental;
import com.equipment.school_equipment.message.error.Message;
import com.equipment.school_equipment.repository.ClassPeriodRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.repository.RentalRepository;
import com.equipment.school_equipment.repository.dto.TodayRentalSelectDto;
import com.equipment.school_equipment.request.admin.RentalAddRequest;
import com.equipment.school_equipment.request.admin.RentalPageCondition;
import com.equipment.school_equipment.request.rental.RentalCreate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalService {

    private final EquipmentRepository equipmentRepository;
    private final ClassPeriodRepository classPeriodRepository;
    private final RentalRepository rentalRepository;

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
    public void addRental(RentalAddRequest request) {
        ClassPeriod classPeriod = classPeriodRepository.findById(request.getClassroomId()).orElseThrow(IllegalArgumentException::new);
        Equipment equipment = equipmentRepository.findById(request.getEquipmentId()).orElseThrow(IllegalArgumentException::new);

        // 보관하고 있는 장비 수량보다 대여 수량이 더 많으면 에러
        if(equipment.getCount() < request.getRetCnt()) throw new IllegalArgumentException("보관하고 있는 장비 수량보다 대여 수량이 더 많습니다.");

        // 대여한 장비와 수업 시간이 똑같을 경우 처리하는 로직
        rentalRepository.findByClassIdAndEquipmentId(classPeriod.getId(), equipment.getId())
                .ifPresentOrElse(rental1 ->  // 있다면
                            rental1.updateRentalCnt(rental1.getRentalCnt() + request.getRetCnt()),
                        () -> { // 없다면
                            Rental rental = Rental.builder()
                                    .equipment(equipment).classPeriod(classPeriod)
                                    .rentalCnt(request.getRetCnt()).rentalChk(true).build();
                            rentalRepository.save(rental);
                        }
                );
    }

    /**
     * 오늘 요일에 대여된 장비와 남은 장비 빼야됨
     * @param condition
     * @return
     */
    public Page<Rental> findByAll(RentalPageCondition condition) {
        PageRequest pageRequest = PageRequest.of(condition.getPage(), 10);
        if(Objects.isNull(condition.getCategory())) return rentalRepository.findAllAndRentalChkTruePage(condition, pageRequest);
        else return rentalRepository.findAllAndRentalCategoryPage(condition,pageRequest);
    }

    /**
     * 남은수 = (가지고 있던 수량 - 렌탈 장비 수)
     *
     * @param equipmentName 장비 이름 받아야됨
     * @return 남은갯수 반환
     */
    public int getEquipmentCnt(String equipmentName) {
        List<Rental> findRental = rentalRepository.findRentals(equipmentName);
        int count = equipmentRepository.findByName(equipmentName).getCount();
        for (Rental rentalsObj : findRental) count -= rentalsObj.getRentalCnt();

        return count;
    }

    public List<Equipment> getClassnameIdAndDayOfWeek(String classNameId, String dayOfWeek) {
        List<Equipment> equipments = rentalRepository.findByClassnameIdAndDayOfWeek(classNameId, dayOfWeek);

        return equipments;
    }

    private static int valedationLeftEquipment(RentalCreate request, Equipment equipment) {
        int verification = equipment.getCount() - request.equipmentCount();
        if (verification < 0) throw new IllegalArgumentException();
        return verification;
    }

    @Transactional
    public void rentalDelete(Long id) {
        Rental rental = getRental(id);
        rentalRepository.deleteById(rental.getId());
    }

    @Transactional
    public void rentalCntUpdate(Long id, int rentalCnt) throws RuntimeException{
            Rental rental = this.getRental(id);
            if(rental.getEquipment().getCount() < rentalCnt) {
                throw new IllegalArgumentException(Message.RENTAL_MAX_ERROR);
            }
            rental.updateRentalCnt(rentalCnt);
    }

    public Rental getRental(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Message.RENTAL_FIND_ERROR));
    }

    @Transactional
    public void rentalUpdate(Rental updateRental) {
        Rental findRental = getRental(updateRental.getId());
        findRental.updateRental(updateRental);
    }

    public Page<TodayRentalSelectDto> findByEquipmentJoinToday(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return rentalRepository.findRentalJoinTodayRental(pageRequest);
    }

    public Page<TodayRentalSelectDto> findCategoryAndEquipment(String category, int page){
        PageRequest pageRequest = PageRequest.of(page, 15);
        return rentalRepository.findCategoryAndEquipment(category, pageRequest);
    }


}
