package com.dis.reservationservice.service;

import com.dis.reservationservice.dto.ReservationLineItemsDto;
import com.dis.reservationservice.dto.ReservationRequest;
import com.dis.reservationservice.dto.TreatmentAvailabilityResponse;
import com.dis.reservationservice.event.ReservationMadeEvent;
import com.dis.reservationservice.model.Reservation;
import com.dis.reservationservice.model.ReservationLineItems;
import com.dis.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, ReservationMadeEvent> kafkaTemplate;

    public String makeReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(UUID.randomUUID().toString());

        List<ReservationLineItems> reservationLineItems = reservationRequest.getReservationLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        reservation.setReservationLineItemsList(reservationLineItems);

        List<String> itemCodes = reservation.getReservationLineItemsList().stream()
                .map(ReservationLineItems::getItemCode)
                .toList();

        //call Availability service and make reservation if treatment is available, for that we will use web client
        TreatmentAvailabilityResponse[] treatmentAvailabilityResponseArray = webClientBuilder.build().get()
                .uri("http://treatmentAvailability-service/api/treatmentAvailability",
                        uriBuilder -> uriBuilder.queryParam("itemCode", itemCodes).build())
                .retrieve()
                .bodyToMono(TreatmentAvailabilityResponse[].class) //bodytoMono we need to write in order to receive response from web client
                .block(); //make call syncronized

        //if isAvailable is true for every treatment then allTreatmentsAvailable will be true, otherwise it will be false
       boolean allTreatmentsAvailable = Arrays.stream(treatmentAvailabilityResponseArray)
               .allMatch(TreatmentAvailabilityResponse::isAvailable);

       //if every treatment from reservation is available we will save reservation in db
        if (allTreatmentsAvailable){
            reservationRepository.save(reservation);
            kafkaTemplate.send("notificationTopic", new ReservationMadeEvent(reservation.getReservationNumber()));
            return "Reservation made successfully";
        } else {
            throw new IllegalArgumentException("Treatment is not available, please try again later");
        }


    }

    private ReservationLineItems mapToDto(ReservationLineItemsDto reservationLineItemsDto) {
        ReservationLineItems reservationLineItems = new ReservationLineItems();
        reservationLineItems.setPrice(reservationLineItemsDto.getPrice());
        reservationLineItems.setQuantity(reservationLineItemsDto.getQuantity());
        reservationLineItems.setItemCode(reservationLineItemsDto.getItemCode());
        return reservationLineItems;
    }
}

