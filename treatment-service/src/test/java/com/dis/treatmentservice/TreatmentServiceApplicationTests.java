package com.dis.treatmentservice;

import com.dis.treatmentservice.dto.TreatmentRequest;
import com.dis.treatmentservice.repository.TreatmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class TreatmentServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.14-rc0-focal");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private TreatmentRepository treatmentRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateTreatment() throws Exception {
		TreatmentRequest treatmentRequest = getTreatmentRequest();
		String treatmentRequestString = objectMapper.writeValueAsString(treatmentRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/treatment")
						.contentType(MediaType.APPLICATION_JSON)
						.content(treatmentRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, treatmentRepository.findAll().size());
	}
	private TreatmentRequest getTreatmentRequest() {
		return TreatmentRequest.builder()
				.name("male haircut")
				.description("test desc")
				.location("Novi Sad")
				.price(BigDecimal.valueOf(500))
				.build();
	}
}
