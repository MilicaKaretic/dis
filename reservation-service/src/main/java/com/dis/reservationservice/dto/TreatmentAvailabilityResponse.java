package com.dis.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreatmentAvailabilityResponse {
    //we are duplicating this class in reservation service because we can't access
    //TreatmentAvailabilityResponse class from treatmentA-availability-service
    private String itemCode;
    private boolean isAvailable;
}