package com.equipment.school_equipment.controller.api;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.response.api.ClasstimeApiFindResponse;
import com.equipment.school_equipment.service.ClassPeriodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/class-period")
public class ClassPeriodApiController {
    private final ClassPeriodService classPeriodService;

    @GetMapping("{weekday}")
    public ResponseEntity<List<ClasstimeApiFindResponse>> findClassesByWeek(@PathVariable String weekday) {
        List<ClassPeriod> classPeriodList = classPeriodService.findByDay(weekday);
        List<ClasstimeApiFindResponse> responseList = classPeriodList.stream().map(classes -> ClasstimeApiFindResponse.builder()
                .id(classes.getId())
                .classNames(classes.getClassName())
                .build()).toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
