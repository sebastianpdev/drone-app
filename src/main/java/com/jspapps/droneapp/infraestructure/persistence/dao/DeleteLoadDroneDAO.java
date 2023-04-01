package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.port.DeleteLoadDronePort;
import com.jspapps.droneapp.infraestructure.persistence.DroneLoadRepository;
import lombok.AllArgsConstructor;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@PersistenceAdapter
public class DeleteLoadDroneDAO implements DeleteLoadDronePort {

    private final Logger logger = Logger.getLogger(DeleteLoadDroneDAO.class.getName());
    private final DroneLoadRepository droneLoadRepository;

    @Override
    public void cleanLoadDeliveredByDrone(String droneId) {
        logger.log(Level.INFO, MessageFormat.format("Trying clean load registered for drone {0}", droneId));
        droneLoadRepository.cleanLoadDeliveredByDrone(droneId);
    }
}
