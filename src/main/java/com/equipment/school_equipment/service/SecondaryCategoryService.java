package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.repository.PrimaryCategoryRepository;
import com.equipment.school_equipment.repository.SecondaryCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SecondaryCategoryService {
    private final SecondaryCategoryRepository secondaryCategoryRepository;
    private final PrimaryCategoryRepository primaryCategoryRepository;

    @Transactional
    public SecondaryCategory addCategory(String primaryCategoryName, String name) {

        PrimaryCategory primaryCategory = primaryCategoryRepository.findByCategoryName(primaryCategoryName).orElseThrow(NullPointerException::new);

        SecondaryCategory secondaryCategory = SecondaryCategory.builder()
                .primaryCategory(primaryCategory)
                .categoryName(name)
                .build();

        // 카테고리 중복 검증
        secondaryCategoryRepository.findByCategoryName(name)
                .ifPresentOrElse(categoryAction -> {
                            throw new RuntimeException("이미 존재하는 카테고리입니다.");
                        },
                        () -> secondaryCategoryRepository.save(secondaryCategory));
        return secondaryCategory;
    }

    public Page<SecondaryCategory> findAll(int pageNumber) {
        return secondaryCategoryRepository.findAll(PageRequest.of(pageNumber, 10));
    }

    public List<SecondaryCategory> findAll() {
        return secondaryCategoryRepository.findAll();
    }

    public SecondaryCategory findById(Long id) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("입력하신 카테고리는 없습니다."));
        return secondaryCategory;
    }

    @Transactional
    public SecondaryCategory findByIdAndName(Long id, String categoryName, String changeName) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findByIdAndCategoryName(id, categoryName).orElseThrow(() -> new RuntimeException("잘못된 접근입니다."));
        secondaryCategory.updateName(changeName);
        return secondaryCategory;
    }

    /**
     * @param categoryId 카테고리아이디
     * @return 삭제완료 0, 삭제 실패 1
     */
    @Transactional
    public int deleteById(Long categoryId) {
        // 카테고리 아이디로 장비가 있는지 검증
        secondaryCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("입력하신 카테고리는 없습니다"));

        if (secondaryCategoryRepository.countEquipment(categoryId) > 0) {
            throw new RuntimeException("입력하신 카테고리에 장비가 들어있습니다.");
        }

        secondaryCategoryRepository.deleteById(categoryId);
        return 0;
    }

    public List<SecondaryCategory> findByPrimaryCategory(String primaryCategory) {
        List<SecondaryCategory> findSecondaryCategory = secondaryCategoryRepository.findByPrimaryCategories(primaryCategory);
        return findSecondaryCategory;
    }
}
