package com.equipment.school_equipment.service;

import com.equipment.school_equipment.domain.PrimaryCategory;
import com.equipment.school_equipment.message.error.Message;
import com.equipment.school_equipment.repository.PrimaryCategoryRepository;
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
public class PrimaryCategoryService {
    private final PrimaryCategoryRepository primaryCategoryRepository;

    public List<PrimaryCategory> findAll() {
        return primaryCategoryRepository.findAll();
    }

    public Page<PrimaryCategory> findAllPage(int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        return primaryCategoryRepository.findAll(pageRequest);
    }

    @Transactional
    public void add(PrimaryCategory primaryCategory) {
        Objects.requireNonNull(primaryCategory, "이름이 없습니다");
        primaryCategoryRepository.save(primaryCategory);
    }

    @Transactional
    public void deleteById(long primaryCategoryId) {
        PrimaryCategory findPrimaryCategory = getPrimaryCategory(primaryCategoryId);
        primaryCategoryRepository.delete(findPrimaryCategory);
    }

    private PrimaryCategory getPrimaryCategory(long primaryCategoryId) {
        return primaryCategoryRepository
                .findById(primaryCategoryId)
                .orElseThrow(() -> new EntityNotFoundException(Message.CATEGORY_FIND_ERROR));
    }

    public PrimaryCategory findById(long primaryCategoryId) {
        return getPrimaryCategory(primaryCategoryId);
    }

    @Transactional
    public void changeCategoryName(Long id, String changeNameClassname) {
        PrimaryCategory findPrimaryCategory = getPrimaryCategory(id);
        findPrimaryCategory.updateChangeName(changeNameClassname);
    }
}
