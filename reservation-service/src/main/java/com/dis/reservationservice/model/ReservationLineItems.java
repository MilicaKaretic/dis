package com.dis.reservationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_reservation_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;

    //haircut, hair styling, lash lift etc
}
