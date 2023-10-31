package com.dis.reservationservice.controller;


import com.dis.reservationservice.dto.ReservationRequest;
import com.dis.reservationservice.service.ReservationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    //service injection
    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "treatmentAvailability", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "treatmentAvailability")
    @Retry(name = "treatmentAvailability")
    public CompletableFuture<String> makeReservation(@RequestBody ReservationRequest reservationRequest) {
        return CompletableFuture.supplyAsync(() -> reservationService.makeReservation(reservationRequest)) ;
    }

    public CompletableFuture<String> fallbackMethod(ReservationRequest reservationRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Something went wrong, please try to make reservation after some time") ;
    }
}