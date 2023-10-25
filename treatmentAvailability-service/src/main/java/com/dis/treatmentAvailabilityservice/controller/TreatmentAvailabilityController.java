package com.dis.treatmentAvailabilityservice.controller;

import com.dis.treatmentAvailabilityservice.service.TreatmentAvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatmentAvailability")
@RequiredArgsConstructor
@Slf4j
public class TreatmentAvailabilityController {

    private final TreatmentAvailabilityService treatmentAvailabilityService;
    @GetMapping("/{item-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAvailable(@PathVariable("item-code") String itemCode) {
        //this will check if treatment is available or not
        return treatmentAvailabilityService.isAvailable(itemCode);
    }
}
