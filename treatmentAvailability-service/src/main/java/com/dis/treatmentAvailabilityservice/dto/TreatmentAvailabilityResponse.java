package com.dis.treatmentAvailabilityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreatmentAvailabilityResponse {
    private String itemCode;
    private boolean isAvailable;
}