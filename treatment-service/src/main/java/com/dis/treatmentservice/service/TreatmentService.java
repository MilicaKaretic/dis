package com.dis.treatmentservice.service;

import com.dis.treatmentservice.dto.TreatmentRequest;
import com.dis.treatmentservice.dto.TreatmentResponse;
import com.dis.treatmentservice.model.Treatment;
import com.dis.treatmentservice.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TreatmentService {

    @Autowired
    private final TreatmentRepository treatmentRepository;

    public void createTreatment(TreatmentRequest treatmentRequest) {
        Treatment treatment = Treatment.builder()
                .name(treatmentRequest.getName())
                .description(treatmentRequest.getDescription())
                .location(treatmentRequest.getLocation())
                .price(treatmentRequest.getPrice())
                .build();

        treatmentRepository.save(treatment);
        log.info("Treatment {} is saved", treatment.getId());
    }

    public List<TreatmentResponse> getAllTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();

        return treatments.stream().map(this::mapToTreatmentResponse).toList();
    }

    private TreatmentResponse mapToTreatmentResponse(Treatment treatment) {
        return TreatmentResponse.builder()
                .id(treatment.getId())
                .name(treatment.getName())
                .description(treatment.getDescription())
                .location(treatment.getLocation())
                .price(treatment.getPrice())
                .build();
    }
}