package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.port.ListMedicationLoadPort;
import com.jspapps.droneapp.infraestructure.persistence.DroneLoadRepository;
import lombok.AllArgsConstructor;

import java.util.logging.Logger;

@AllArgsConstructor
@PersistenceAdapter
public class ListMedicationLoadDAO implements ListMedicationLoadPort {

    private final Logger logger = Logger.getLogger(ListMedicationLoadDAO.class.getName());
    private final DroneLoadRepository droneLoadRepository;

    @Override
    public Long currentMedicationLoadByDrone(String droneId) {
        var currentWeight = droneLoadRepository.currentMedicationLoadByDrone(droneId);
        if (currentWeight == null) {
            return 0L;
        }
        return currentWeight;
    }

}
