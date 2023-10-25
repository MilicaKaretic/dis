package com.dis.treatmentAvailabilityservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_treatmentAvailability")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //treatment
    private String itemCode;
    private String location;
    //number of available treatments
    private Integer quantity;
}