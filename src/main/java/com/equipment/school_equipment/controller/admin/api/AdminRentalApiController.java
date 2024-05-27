package com.equipment.school_equipment.controller.admin.api;

import com.equipment.school_equipment.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
