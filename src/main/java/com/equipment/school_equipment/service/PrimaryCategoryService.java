package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.repository.PrimaryCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrimaryCategoryService {
    private final PrimaryCategoryRepository primaryCategoryRepository;

    public List<PrimaryCategory> findAll() {
        return primaryCategoryRepository.findAll();
    }

    @Transactional
    public void add(PrimaryCategory primaryCategory) {
        Objects.requireNonNull(primaryCategory, "이름이 없습니다");
        primaryCategoryRepository.save(primaryCategory);
    }

    @Transactional
    public void deleteById(long primaryCategoryId) {
        PrimaryCategory findPrimaryCategory = primaryCategoryRepository.findById(primaryCategoryId).orElseThrow(IllegalAccessError::new);
        primaryCategoryRepository.delete(findPrimaryCategory);
    }
}
