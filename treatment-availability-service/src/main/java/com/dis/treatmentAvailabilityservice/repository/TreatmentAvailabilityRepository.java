package com.dis.treatmentAvailabilityservice.repository;

import com.dis.treatmentAvailabilityservice.model.TreatmentAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TreatmentAvailabilityRepository extends JpaRepository<TreatmentAvailability, Long> {

    List<TreatmentAvailability> findByItemCodeIn(List<String> itemCode);
}
