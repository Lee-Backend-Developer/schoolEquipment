package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.response.api.ApiResponse;
import com.equipment.school_equipment.response.api.PrimaryApiCategory;
import com.equipment.school_equipment.service.ClassPeriodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/class-period")
public class AdminClassPeriodApiController {
    private final ClassPeriodService classPeriodService;

    //요청: 월요일, 응답: 요일에 해당하는 수업 목록
    @GetMapping
    public ResponseEntity getRentalWeek(@RequestParam String week) {
        List<PrimaryApiCategory> responseBody = classPeriodService.findByRentalWeek(week).stream()
                .map(classPeriod ->
                        PrimaryApiCategory.builder().id(classPeriod.getId()).name(classPeriod.getClassName()).build()).toList();
        ApiResponse response = new ApiResponse(HttpStatus.OK, responseBody);
        return ResponseEntity.ofNullable(response);
    }
}
