package com.equipment.school_equipment.controller.api;

import com.equipment.school_equipment.domain.Classes;
import com.equipment.school_equipment.response.api.ClasstimeApiFindResponse;
import com.equipment.school_equipment.service.ClassTimeService;
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
@RequestMapping("/api/classtimes")
public class ClasstimeApiController {
    private final ClassTimeService classTimeService;

    @GetMapping("{weekday}")
    public ResponseEntity<List<ClasstimeApiFindResponse>> classtimes(@PathVariable String weekday) {
        List<Classes> classtimes = classTimeService.findByDay(weekday);
        List<ClasstimeApiFindResponse> responseList = classtimes.stream().map(classtime -> ClasstimeApiFindResponse.builder()
                .id(classtime.getClassesId())
                .classNames(classtime.getClassName())
                .build()).toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
