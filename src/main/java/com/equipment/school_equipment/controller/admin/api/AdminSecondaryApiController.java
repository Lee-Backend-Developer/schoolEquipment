package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.response.api.SecondaryCategoryApiFindResponse;
import com.equipment.school_equipment.service.SecondaryCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/category/{primaryCategory}")
public class AdminSecondaryApiController {
    private final SecondaryCategoryService secondaryCategoryService;

    @GetMapping
    public ResponseEntity<List<SecondaryCategoryApiFindResponse>> getByPrimaryList(@PathVariable(name = "primaryCategory") String primaryCategory){
        List<SecondaryCategoryApiFindResponse> responseCategoryList = secondaryCategoryService.findByPrimaryCategory(primaryCategory)
                .stream().map(category -> SecondaryCategoryApiFindResponse.builder()
                        .id(category.getId()).categoryName(category.getCategoryName()).build())
                .toList();

        return ResponseEntity.ok(responseCategoryList);
    }

}
