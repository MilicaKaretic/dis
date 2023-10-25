package com.dis.treatmentAvailabilityservice.service;

import com.dis.treatmentAvailabilityservice.repository.TreatmentAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TreatmentAvailabilityService {

    private final TreatmentAvailabilityRepository treatmentAvailabilityRepository;

    @Transactional(readOnly = true)
    public boolean isAvailable(String itemCode){

        return treatmentAvailabilityRepository.findByItemCode(itemCode).isPresent();
    }
}
