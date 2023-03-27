package com.jspapps.droneapp;

import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.application.util.constant.DroneType;
import com.jspapps.droneapp.infraestructure.persistence.DroneRepository;
import com.jspapps.droneapp.infraestructure.persistence.MedicationRepository;
import com.jspapps.droneapp.infraestructure.persistence.model.Drone;
import com.jspapps.droneapp.infraestructure.persistence.model.Medication;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class DroneAppApplication {

	private final DroneRepository droneRepository;
	private final MedicationRepository medicationRepository;

	public DroneAppApplication(DroneRepository droneRepository, MedicationRepository medicationRepository) {
		this.droneRepository = droneRepository;
		this.medicationRepository = medicationRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DroneAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(50);
		executor.setQueueCapacity(100);
		executor.initialize();
		return executor;
	}

	@PostConstruct
	public void init() {
		medicationRepository.save(Medication.builder()
						.id("AASDSADFFV")
						.name("Aspirin")
						.code("A5H6_9B7D")
						.weight(100L)
				.build()
		);

		droneRepository.save(Drone.builder()
				.id("99904740c14c49ef8096")
				.serial("QWERTY")
				.model(DroneType.LIGHTWEIGHT)
				.weight(500L)
				.battery(100L)
				.state(DroneState.IDLE)
				.build()
		);
	}

}
