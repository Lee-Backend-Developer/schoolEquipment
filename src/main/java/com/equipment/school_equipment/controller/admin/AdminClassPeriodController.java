package com.equipment.school_equipment.controller.admin;

import com.equipment.school_equipment.domain.classPeriod.ClassPeriod;
import com.equipment.school_equipment.request.admin.ClassPeriodPageCondition;
import com.equipment.school_equipment.request.admin.ClassmateRequest;
import com.equipment.school_equipment.request.classTime.ClassTimeCreate;
import com.equipment.school_equipment.request.classTime.ClassTimeUpdate;
import com.equipment.school_equipment.response.thymeleaf.admin.ClasstimeResponse;
import com.equipment.school_equipment.service.ClassPeriodService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/class-period")
public class AdminClassPeriodController {
    private final ClassPeriodService classPeriodService;

    @GetMapping
    public String findByAll(@ModelAttribute(binding = false) ClassPeriodPageCondition page, Model model) {

        Page<ClassPeriod> classPeriodPage = classPeriodService.findAllPage(page);

        model.addAttribute("classPeriodContent", classPeriodPage.getContent());
        model.addAttribute("classPeriodPage", classPeriodPage);

        return "admin/classPeriod/find-all";
    }

    @GetMapping("/add")
    public String adminClassesAdd(Model model) {
        model.addAttribute("classmateRequest", ClassmateRequest.builder()
                        .build());
        return "admin/classPeriod/add";
    }

    @PostMapping("/add")
    public String adminClassesAdd(@Valid @ModelAttribute("classmateRequest") ClassmateRequest classmateRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "admin/classPeriod/add";
        }
        ClassTimeCreate classTimeCreate = ClassTimeCreate.builder()
                .className(classmateRequest.getClassname())
                .dayOfWeek(classmateRequest.getDayOfWeekEnum())
                .oneTime(classmateRequest.isOneTime())
                .twoTime(classmateRequest.isTwoTime())
                .threeTime(classmateRequest.isThreeTime())
                .fourTime(classmateRequest.isFourTime())
                .fiveTime(classmateRequest.isFiveTime())
                .sixTime(classmateRequest.isSixTime())
                .sevenTime(classmateRequest.isSevenTime())
                .eightTime(classmateRequest.isEightTime())
                .nineTime(classmateRequest.isNineTime())
                .tenTime(classmateRequest.isTenTime())
                .build();
        classPeriodService.save(classTimeCreate);

        return "redirect:/admin/class-period";
    }


    @GetMapping("/edit/{classnameId}")
    public String adminClassesEdit(@PathVariable Long classnameId, Model model) {
        ClassPeriod classTimes = classPeriodService.findById(classnameId);

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
                .eightTime(classTimes.isEightTime())
                .nineTime(classTimes.isNineTime())
                .tenTime(classTimes.isTenTime())
                .build();

        model.addAttribute("classtime", classmateRequest);

        return "admin/classPeriod/edit";
    }

    @PostMapping("/edit/{classnameId}")
    public String adminClassesAdd(@Valid @ModelAttribute(name = "classtime") ClassmateRequest classmateRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "admin/classPeriod/edit";
        }
        ClassPeriod findByClassPeriod = classPeriodService.findById(classmateRequest.getClassnameId());

        ClassTimeUpdate classTimeUpdate = ClassTimeUpdate.builder()
                .updateClassname(findByClassPeriod.getClassName(), classmateRequest.getClassname())
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

        classPeriodService.updateClassTime(classTimeUpdate);

        return "redirect:/admin/class-period";
    }

    @GetMapping("/delete")
    public String adminClassesDelete(Model model) {
        List<ClassPeriod> classTimesList = classPeriodService.findAll();
        List<ClasstimeResponse> responseList = classTimesList.stream().map(classtimes -> ClasstimeResponse.builder().id(classtimes.getId()).classname(classtimes.getClassName()).build()).toList();
        model.addAttribute("responses", responseList);
        return "admin/classPeriod/delete";
    }

    @GetMapping("/delete/{classtimesId}")
    public void adminClassesDelete(@PathVariable("classtimesId") Long classtimesId, HttpServletResponse response) throws IOException {
        classPeriodService.delete(classtimesId);
        response.sendRedirect("/admin/class-period");
    }

}