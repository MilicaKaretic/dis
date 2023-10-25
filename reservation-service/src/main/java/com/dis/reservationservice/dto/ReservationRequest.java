package com.dis.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private List<ReservationLineItemsDto> reservationLineItemsDtoList;
}
