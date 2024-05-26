package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category addCategory(String name) {
        Category category = Category.builder()
                .categoryName(name)
                .build();

        // 카테고리 중복 검증
        categoryRepository.findByCategoryName(name)
                .ifPresentOrElse(categoryAction -> {
                            throw new RuntimeException("이미 존재하는 카테고리입니다.");
                        },
                        () -> categoryRepository.save(category));
        return category;
    }

    public Page<Category> findAll(int pageNumber) {
        return categoryRepository.findAll(PageRequest.of(pageNumber, 10));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("입력하신 카테고리는 없습니다."));
        return category;
    }

    @Transactional
    public Category findByIdAndName(Long id, String categoryName, String changeName) {
        Category category = categoryRepository.findByIdAndCategoryName(id, categoryName).orElseThrow(() -> new RuntimeException("잘못된 접근입니다."));
        category.updateName(changeName);
        return category;
    }

    /**
     * @param categoryId 카테고리아이디
     * @return 삭제완료 0, 삭제 실패 1
     */
    @Transactional
    public int deleteById(Long categoryId) {
        // 카테고리 아이디로 장비가 있는지 검증
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("입력하신 카테고리는 없습니다"));

        if (categoryRepository.countEquipment(categoryId) > 0) {
            throw new RuntimeException("입력하신 카테고리에 장비가 들어있습니다.");
        }

        categoryRepository.deleteById(categoryId);
        return 0;
    }
}
