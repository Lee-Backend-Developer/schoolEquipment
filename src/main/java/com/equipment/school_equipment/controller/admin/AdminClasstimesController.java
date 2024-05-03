package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.response.thymeleaf.ClasstimeResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.ClasstimesFindResponse;
import com.equipment.school_equipment.service.ClassTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/classtimes")
public class AdminClasstimesController {
    private final ClassTimeService classTimeService;

    @GetMapping
    public String adminClasstimes(Model model) {
        List<Classtimes> classtimes = classTimeService.findAll();
        List<ClasstimesFindResponse> responses = classtimes.stream()
                .map((classtime -> ClasstimesFindResponse.builder()
                        .id(classtime.getId())
                        .name(classtime.getClassName())
                        .build())).toList();

        model.addAttribute("classtimes", responses);

        return "admin/classtimes/classtimesFindAll";
    }
}
