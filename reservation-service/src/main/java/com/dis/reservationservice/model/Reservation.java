package com.dis.reservationservice.model;

import com.dis.reservationservice.model.ReservationLineItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reservationNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ReservationLineItems> reservationLineItemsList;

    //one reservation can contain list of treatments
}