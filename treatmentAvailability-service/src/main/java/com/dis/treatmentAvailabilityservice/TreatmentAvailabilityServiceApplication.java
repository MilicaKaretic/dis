package com.dis.treatmentAvailabilityservice;

import com.dis.treatmentAvailabilityservice.model.TreatmentAvailability;
import com.dis.treatmentAvailabilityservice.repository.TreatmentAvailabilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TreatmentAvailabilityServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(TreatmentAvailabilityServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(TreatmentAvailabilityRepository treatmentAvailabilityRepository){
		return args -> {
			TreatmentAvailability treatmentAvailability = new TreatmentAvailability();
			treatmentAvailability.setItemCode("male haircut");
			treatmentAvailability.setLocation("Novi Sad");
			treatmentAvailability.setQuantity(100);

			TreatmentAvailability treatmentAvailability1 = new TreatmentAvailability();
			treatmentAvailability1.setItemCode("female haircut");
			treatmentAvailability1.setLocation("Belgrade");
			treatmentAvailability1.setQuantity(20);

			treatmentAvailabilityRepository.save(treatmentAvailability);
			treatmentAvailabilityRepository.save(treatmentAvailability1);
		};
	}
}
