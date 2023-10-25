package com.dis.treatmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentResponse {
    private String id;
    private String name;
    private String description;
    private String location;
    private BigDecimal price;
}