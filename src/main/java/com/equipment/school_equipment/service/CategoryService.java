package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.Category;
import com.equipment.school_equipment.repository.EquipmentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;

    @Transactional
    public Category addCategory(String name) {
        Category category = Category.builder().categoryName(name).build();
        return equipmentCategoryRepository.save(category);
    }

    public List<Category> findAll() {
            return equipmentCategoryRepository.findAll();
    }

    public Category findById(Long id) {
        Category category = equipmentCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("입력하신 카테고리는 없습니다."));
        return category;
    }

    @Transactional
    public Category findByIdAndName(Long id, String categoryName, String changeName) {
        Category category = equipmentCategoryRepository.findByIdAndCategoryName(id, categoryName).orElseThrow(() -> new RuntimeException("잘못된 접근입니다."));
        category.updateName(changeName);
        return category;
    }
}
