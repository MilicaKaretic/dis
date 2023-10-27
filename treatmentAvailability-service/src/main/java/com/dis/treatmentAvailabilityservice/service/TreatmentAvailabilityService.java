package com.dis.treatmentAvailabilityservice.service;

import com.dis.treatmentAvailabilityservice.dto.TreatmentAvailabilityResponse;
import com.dis.treatmentAvailabilityservice.repository.TreatmentAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TreatmentAvailabilityService {

    private final TreatmentAvailabilityRepository treatmentAvailabilityRepository;

    @Transactional(readOnly = true)
    public List<TreatmentAvailabilityResponse> isAvailable(List<String> itemCode){

        return treatmentAvailabilityRepository.findByItemCodeIn(itemCode).stream()
                .map(treatmentAvailability ->
                    TreatmentAvailabilityResponse.builder()
                            .itemCode((treatmentAvailability.getItemCode()))
                            .isAvailable(treatmentAvailability.getQuantity()>0)
                            .build()
                ).toList();
    }
}
