package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.domain.SecondaryCategory;
import com.equipment.school_equipment.service.SecondaryCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/category/api/{primaryCategory}")
public class AdminSecondaryApiController {
    private final SecondaryCategoryService secondaryCategoryService;

    @GetMapping
    public ResponseEntity<List<String>> getByPrimaryList(@PathVariable(name = "primaryCategory") String primaryCategory){
        List<SecondaryCategory> primaryCategoryList = secondaryCategoryService.findByPrimaryCategory(primaryCategory);

        return ResponseEntity.ok(primaryCategoryList
                .stream()
                .map(SecondaryCategory::getCategoryName).toList());
    }

}
