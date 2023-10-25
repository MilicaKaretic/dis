package com.dis.treatmentservice.controller;

import com.dis.treatmentservice.dto.TreatmentRequest;
import com.dis.treatmentservice.dto.TreatmentResponse;
import com.dis.treatmentservice.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatment")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTreatment(@RequestBody TreatmentRequest treatmentRequest) {
        treatmentService.createTreatment(treatmentRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentResponse> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }
}