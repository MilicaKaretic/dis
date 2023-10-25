package com.dis.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationLineItemsDto {
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
