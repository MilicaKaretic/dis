package com.dis.reservationservice.service;

import com.dis.reservationservice.dto.ReservationLineItemsDto;
import com.dis.reservationservice.dto.ReservationRequest;
import com.dis.reservationservice.model.Reservation;
import com.dis.reservationservice.model.ReservationLineItems;
import com.dis.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void makeReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(UUID.randomUUID().toString());

        List<ReservationLineItems> reservationLineItems = reservationRequest.getReservationLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        reservation.setReservationLineItemsList(reservationLineItems);

        reservationRepository.save(reservation);
    }

    private ReservationLineItems mapToDto(ReservationLineItemsDto reservationLineItemsDto) {
        ReservationLineItems reservationLineItems = new ReservationLineItems();
        reservationLineItems.setPrice(reservationLineItemsDto.getPrice());
        reservationLineItems.setQuantity(reservationLineItemsDto.getQuantity());
        reservationLineItems.setItemCode(reservationLineItemsDto.getItemCode());
        return reservationLineItems;
    }
}

