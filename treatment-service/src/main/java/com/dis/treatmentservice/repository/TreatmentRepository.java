package com.dis.treatmentservice.repository;

import com.dis.treatmentservice.model.Treatment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreatmentRepository extends MongoRepository<Treatment, String> {
}