package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.domain.Equipment;
import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class SecondaryCategoryRepositoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private CategoryService categoryService;

    @AfterEach
    void end() {
        equipmentRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @DisplayName("카메라 라는 카테고리가 만들어져야한다")
    @Test
    void create_O(){
        //given
        String name = "카메라";

        //when
        categoryService.addCategory(name);

        //then
        SecondaryCategory secondaryCategory = categoryRepository.findByCategoryName(name).get();
        assertThat(secondaryCategory.getCategoryName()).isEqualTo(name);

    }

    @DisplayName("카테고리 고유명인 아이디 입력 받으면 카테고리 정보가 나와야한다")
    @Test
    void findById_O() {
        //given
        SecondaryCategory camara = SecondaryCategory.builder().categoryName("카메라").build();
        categoryRepository.save(camara);

        Long id = camara.getId();

        //when
        SecondaryCategory secondaryCategory = categoryService.findById(id);

        //then
        assertThat(secondaryCategory.getId()).isEqualTo(id);

    }

    @DisplayName("ID, 이름 입력을 받아 카테고리 이름 변경이 되어야한다.")
    @Test
    void findByIdAndName_O() throws Exception {
        //given 준비 과정
        SecondaryCategory secondaryCategory = categoryRepository.save(SecondaryCategory.builder().categoryName("카메라").build());

        //when 실행
        SecondaryCategory findSecondaryCategory = categoryService.findByIdAndName(secondaryCategory.getId(), secondaryCategory.getCategoryName(), "마이크");

        //then or expect 검증
        assertThat(findSecondaryCategory.getId()).isEqualTo(secondaryCategory.getId());
        assertThat(findSecondaryCategory.getCategoryName()).isEqualTo("마이크");
    }

    @DisplayName("카메라, 마이크 이 두개가 조회가 되어야 한다")
    @Test
    void findCategory_O(){
        //given
        SecondaryCategory camara = SecondaryCategory.builder().categoryName("카메라").build();
        SecondaryCategory mic = SecondaryCategory.builder().categoryName("마이크").build();
        categoryRepository.save(camara);
        categoryRepository.save(mic);

        //when

        List<SecondaryCategory> secondaryCategoryList = categoryService.findAll();
        //then
        assertThat(secondaryCategoryList).hasSize(2);
        assertThat(secondaryCategoryList).contains(camara, mic);

    }

    @DisplayName("카테고리 삭제하기 전에 등록된 장비가 있으면 카테고리는 삭제 못하게 해야한다.")
    @Test
    void catetory_delete_X() throws Exception {
        //given 준비 과정
        SecondaryCategory camara = SecondaryCategory.builder().categoryName("카메라").build();
        categoryRepository.save(camara);

        Equipment saveEquipment = Equipment.builder().name("캐논").count(10)
                .build();
        equipmentRepository.save(saveEquipment);
        saveEquipment.addCategory(camara);

        //then 검증 <- 삭제 못하게 예외처리
        assertThatRuntimeException().isThrownBy(() -> categoryService.deleteById(camara.getId()));

    }

    @DisplayName("없는 카테고리 삭제할 때 오류가 발생해야됨")
    @Test
    void category_delete_error_O() throws Exception {

        //then 검증
        assertThatRuntimeException().isThrownBy(() -> categoryService.deleteById(1L));


    }

    @DisplayName("카테고리 삭제하기 전에 등록된 장비가 없으면 삭제가 되어야한다")
    @Test
    void catetory_delete_O() throws Exception {
        //given 준비 과정
        SecondaryCategory camara = SecondaryCategory.builder().categoryName("카메라").build();
        categoryRepository.save(camara);

        //then
        categoryService.deleteById(camara.getId());

        //then 검증 <- 삭제 못하게 예외처리
        assertThat(categoryRepository.findAll().size()).isEqualTo(0);

    }


}