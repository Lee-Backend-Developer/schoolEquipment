package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.repository.CategoryRepository;
import com.equipment.school_equipment.repository.EquipmentRepository;
import com.equipment.school_equipment.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/rental")
public class AdminRentalApiController {
    private final RentalService rentalService;

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> cntUpdate(@PathVariable Long id, @RequestParam int rentalCnt) {
        rentalService.rentalCntUpdate(id, rentalCnt);
        return ResponseEntity.ok().body("edit success");
    }
}
