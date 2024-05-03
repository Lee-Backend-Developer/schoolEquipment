package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Classtimes;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.request.admin.ClasstimesAddRequest;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import com.equipment.school_equipment.response.thymeleaf.admin.ClasstimeResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.ClasstimesFindResponse;
import com.equipment.school_equipment.service.ClassTimeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Arrays;
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

    @GetMapping("/add")
    public String adminClasstimesAdd(Model model) {
        model.addAttribute("classtimeRequest",
                ClasstimesAddRequest.builder()
                        .dayOfWeekEnum(DayOfWeekEnum.values()).build());
        return "admin/classtimes/classtimesAdd";
    }

    @PostMapping("/add")
    public void adminClasstimesAdd(@ModelAttribute("classtimeRequest") ClasstimesAddRequest request, HttpServletResponse response) throws IOException {
        List<String> list = Arrays.stream(request.dayOfWeekEnum()).map(Enum::name).toList();
        DayOfWeekEnum dayOfWeekEnum = DayOfWeekEnum.valueOf(list.get(0));
        ClassTimeCreate classTimeCreate = ClassTimeCreate.builder()
                .className(request.classname())
                .dayOfWeek(dayOfWeekEnum)
                .oneTime(request.oneTime())
                .twoTime(request.twoTime())
                .threeTime(request.threeTime())
                .fourTime(request.fourTime())
                .fiveTime(request.fiveTime())
                .sixTime(request.sixTime())
                .sevenTime(request.sevenTime())
                .nineTime(request.nineTime())
                .tenTime(request.tenTime())
                .build();
        classTimeService.save(classTimeCreate);

        response.sendRedirect("/admin/classtimes");
    }


    @GetMapping("/edit/{classnameId}")
    public String adminClasstimesEdit(@PathVariable Long classnameId, Model model) throws IOException {
        Classtimes classTimes = classTimeService.findById(classnameId);

        ClasstimeResponse classtimeResponse = ClasstimeResponse.builder()
                .classname(classTimes.getClassName())
                .dayOfWeekEnum(classTimes.getDayOfWeek())
                .oneTime(classTimes.isOneTime())
                .twoTime(classTimes.isTwoTime())
                .threeTime(classTimes.isThreeTime())
                .fourTime(classTimes.isFourTime())
                .fiveTime(classTimes.isFiveTime())
                .sixTime(classTimes.isSixTime())
                .sevenTime(classTimes.isSevenTime())
                .nineTime(classTimes.isNineTime())
                .tenTime(classTimes.isTenTime())
                .build();

        model.addAttribute("classtime", classtimeResponse);
        model.addAttribute("dayofweek", DayOfWeekEnum.values());

        return "admin/classtimes/classtimesEdit";
    }

    @PostMapping("/edit/{classnameId}")
    public void adminClasstimesAdd(@PathVariable Long classnameId, @ModelAttribute ClasstimeResponse classtimeResponse, HttpServletResponse response) throws IOException {

        Classtimes findByClasstimes = classTimeService.findById(classnameId);


        ClassTimeUpdate classTimeUpdate = ClassTimeUpdate.builder()
                .updateClassname(findByClasstimes.getClassName(), classtimeResponse.classname())
                .dayOfWeek(classtimeResponse.dayOfWeekEnum())
                .oneTime(classtimeResponse.oneTime())
                .twoTime(classtimeResponse.twoTime())
                .threeTime(classtimeResponse.threeTime())
                .fourTime(classtimeResponse.fourTime())
                .fiveTime(classtimeResponse.fiveTime())
                .sixTime(classtimeResponse.sixTime())
                .sevenTime(classtimeResponse.sevenTime())
                .nineTime(classtimeResponse.nineTime())
                .tenTime(classtimeResponse.tenTime())
                .build();
        classTimeService.updateClassTime(classTimeUpdate);
        response.sendRedirect("/admin/classtimes");
    }

}