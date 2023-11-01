package com.dis.treatmentAvailabilityservice.service;

import com.dis.treatmentAvailabilityservice.dto.TreatmentAvailabilityResponse;
import com.dis.treatmentAvailabilityservice.repository.TreatmentAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    @SneakyThrows
    public List<TreatmentAvailabilityResponse> isAvailable(List<String> itemCode){

        log.info("Wait Started");
        //Thread.sleep(10000);
        log.info("Wait Ended");

        return treatmentAvailabilityRepository.findByItemCodeIn(itemCode).stream()
                .map(treatmentAvailability ->
                    TreatmentAvailabilityResponse.builder()
                            .itemCode((treatmentAvailability.getItemCode()))
                            .isAvailable(treatmentAvailability.getQuantity()>0)
                            .build()
                ).toList();
    }
}
