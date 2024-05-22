package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.Classes;
import com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum;
import com.equipment.school_equipment.request.admin.ClassmateRequest;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import com.equipment.school_equipment.response.thymeleaf.admin.ClasstimeResponse;
import com.equipment.school_equipment.response.thymeleaf.admin.ClasstimesFindResponse;
import com.equipment.school_equipment.service.ClassTimeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/classes")
public class AdminClassesController {
    private static final Logger log = LoggerFactory.getLogger(AdminClassesController.class);
    private final ClassTimeService classTimeService;

    @GetMapping
    public String adminClasses(Model model) {
        List<Classes> classes = classTimeService.findAll();
        List<ClasstimesFindResponse> responses = classes.stream()
                .map((period -> ClasstimesFindResponse.builder()
                        .id(period.getClassesId())
                        .name(period.getClassName())
                        .build())).toList();

        model.addAttribute("classtimes", responses);

        return "admin/classes/find-all";
    }

    @GetMapping("/add")
    public String adminClassesAdd(Model model) {
        model.addAttribute("classtimeRequest",
                ClassmateRequest.builder()
                        .build());
        return "admin/classes/add";
    }

    @PostMapping("/add")
    public String adminClassesAdd(@Valid @ModelAttribute("classtimeRequest") ClassmateRequest request, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "admin/classes/add";
        }
        ClassTimeCreate classTimeCreate = ClassTimeCreate.builder()
                .className(request.getClassname())
                .dayOfWeek(DayOfWeekEnum.friday)
                .oneTime(request.isOneTime())
                .twoTime(request.isTwoTime())
                .threeTime(request.isThreeTime())
                .fourTime(request.isFourTime())
                .fiveTime(request.isFiveTime())
                .sixTime(request.isSixTime())
                .sevenTime(request.isSevenTime())
                .nineTime(request.isNineTime())
                .tenTime(request.isTenTime())
                .build();
        classTimeService.save(classTimeCreate);

        return "redirect:/admin/classtimes";
    }


    @GetMapping("/edit/{classnameId}")
    public String adminClassesEdit(@PathVariable Long classnameId, Model model) {
        Classes classTimes = classTimeService.findById(classnameId);

        ClassmateRequest classmateRequest = ClassmateRequest.builder()
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

        model.addAttribute("classtime", classmateRequest);

        return "admin/classes/edit";
    }

    @PostMapping("/edit/{classnameId}")
    public String adminClassesAdd(@Valid @ModelAttribute(name = "classtime") ClassmateRequest classmateRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "admin/classes/edit";
        }
        Classes findByClasses = classTimeService.findById(classmateRequest.getClassnameId());

        ClassTimeUpdate classTimeUpdate = ClassTimeUpdate.builder()
                .updateClassname(findByClasses.getClassName(), classmateRequest.getClassname())
                .dayOfWeek(classmateRequest.getDayOfWeekEnum())
                .oneTime(classmateRequest.isOneTime())
                .twoTime(classmateRequest.isTwoTime())
                .threeTime(classmateRequest.isThreeTime())
                .fourTime(classmateRequest.isFourTime())
                .fiveTime(classmateRequest.isFiveTime())
                .sixTime(classmateRequest.isSixTime())
                .sevenTime(classmateRequest.isSevenTime())
                .nineTime(classmateRequest.isNineTime())
                .tenTime(classmateRequest.isTenTime())
                .build();

        classTimeService.updateClassTime(classTimeUpdate);

        return "redirect:/admin/classes";
    }

    @GetMapping("/delete")
    public String adminClassesDelete(Model model) {
        List<Classes> classTimesList = classTimeService.findAll();
        List<ClasstimeResponse> responseList = classTimesList.stream().map(classtimes -> ClasstimeResponse.builder().id(classtimes.getClassesId()).classname(classtimes.getClassName()).build()).toList();
        model.addAttribute("responses", responseList);
        return "admin/classes/delete";
    }

    @GetMapping("/delete/{classtimesId}")
    public void adminClassesDelete(@PathVariable("classtimesId") Long classtimesId, HttpServletResponse response) throws IOException {
        classTimeService.delete(classtimesId);
        response.sendRedirect("/admin/classes");
    }

}