package com.jspapps.droneapp.domain.usecase;

import com.jspapps.droneapp.application.util.RandomUUIDGenerator;
import com.jspapps.droneapp.application.util.annotation.UseCase;
import com.jspapps.droneapp.domain.dto.LoadDrone;
import com.jspapps.droneapp.domain.dto.RegisterLoadDrone;
import com.jspapps.droneapp.domain.event.ReviewDroneLoadEvent;
import com.jspapps.droneapp.domain.port.CreateLoadDronePort;
import com.jspapps.droneapp.domain.port.ListMedicationLoadPort;
import com.jspapps.droneapp.domain.port.ListMedicationPort;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@UseCase
public class CreateLoadDroneUseCase {

    private final Logger logger = Logger.getLogger(CreateLoadDroneUseCase.class.getName());
    private static final Long MAX_WEIGHT_ALLOWED = 500L;

    private final ApplicationEventPublisher eventPublisher;
    private final CreateLoadDronePort createLoadDronePort;
    private final ListMedicationLoadPort listMedicationLoadPort;
    private final ListMedicationPort listMedicationPort;

    public boolean registerLoad(LoadDrone load) {
        logger.log(Level.INFO, MessageFormat.format("---> Trying register load to drone {0}", load.getDroneId()));

        if (load.getMedications().isEmpty()) {
            throw new RuntimeException("Medications list is empty.");
        }

        createLoadDronePort.checkDroneToRegisterLoad(load.getDroneId());

        for (Map.Entry<String, Long> medication: checkCurrentLoadDrone(load).entrySet()) {
            var mLoad = RegisterLoadDrone.builder()
                    .id(RandomUUIDGenerator.generateRandomUUIDString())
                    .droneId(load.getDroneId())
                    .medicationId(medication.getKey())
                    .medicationLoad(medication.getValue())
                    .quantity(load.getMedications().get(medication.getKey()))
                    .build();

            createLoadDronePort.registerLoad(mLoad);
        }

        new StartDroneLoadEvent(eventPublisher, load.getDroneId()).start();
        return true;
    }

    private Map<String, Long> checkCurrentLoadDrone(LoadDrone loadDrone) {
        checkLoadDroneMedicationsWeight(loadDrone.getMedications());

        Map<String, Long> finalMedicationWeight = new HashMap<>();
        var totalMedicationWeight = 0L;
        for (Map.Entry<String, Long> medication: loadDrone.getMedications().entrySet()) {
            var medicationWeight = listMedicationPort.findMedicationWeightById(medication.getKey());
            medicationWeight = medicationWeight * medication.getValue();
            totalMedicationWeight += medicationWeight;
            finalMedicationWeight.put(medication.getKey(), medicationWeight);
        }

        if (validateMedicationWeight(totalMedicationWeight)) {
            throw new RuntimeException(MessageFormat.format("The weight of medications list ({0}gr) is not allowed.", totalMedicationWeight));
        }

        var currentMedicationWeightLoaded = listMedicationLoadPort.currentMedicationLoadByDrone(loadDrone.getDroneId());
        var totalWeight = currentMedicationWeightLoaded + totalMedicationWeight;
        if (validateMedicationWeight(totalWeight)) {
            throw new RuntimeException(MessageFormat.format("The weight of medications list ({0}gr) is not allowed. Current load is {1}gr", totalWeight,
                    currentMedicationWeightLoaded));
        }

        return finalMedicationWeight;
    }

    private void checkLoadDroneMedicationsWeight(Map<String, Long> medications) {
        var medicationsWeight = listMedicationPort.sumMedicationsWeightByIds(medications.keySet());
        if (validateMedicationWeight(medicationsWeight)) {
            throw new RuntimeException(MessageFormat.format("The weight of medications list ({0}gr) is not allowed.", medicationsWeight));
        }
    }

    private boolean validateMedicationWeight(Long medicationsWeight) {
        return medicationsWeight > MAX_WEIGHT_ALLOWED;
    }

    private static class StartDroneLoadEvent extends Thread {
        private final String droneId;
        private final ApplicationEventPublisher eventPublisher;

        public StartDroneLoadEvent(ApplicationEventPublisher eventPublisher, String droneId) {
            this.eventPublisher = eventPublisher;
            this.droneId = droneId;
        }

        @Override
        public void run() {
            eventPublisher.publishEvent(new ReviewDroneLoadEvent(droneId));
        }
    }

}
