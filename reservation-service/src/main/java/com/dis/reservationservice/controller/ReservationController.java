package com.dis.reservationservice.controller;


import com.dis.reservationservice.dto.ReservationRequest;
import com.dis.reservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    //service injection
    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String makeReservation(@RequestBody ReservationRequest reservationRequest) {
        reservationService.makeReservation(reservationRequest);
        return "Reservation made Successfully";
    }
}