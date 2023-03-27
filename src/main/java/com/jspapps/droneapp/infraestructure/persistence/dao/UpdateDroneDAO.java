package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.domain.port.UpdateDronePort;
import com.jspapps.droneapp.infraestructure.persistence.DroneRepository;
import lombok.AllArgsConstructor;

import java.util.logging.Logger;

@AllArgsConstructor
@PersistenceAdapter
public class UpdateDroneDAO implements UpdateDronePort {

    private final Logger logger = Logger.getLogger(UpdateDroneDAO.class.getName());
    private final DroneRepository droneRepository;

    @Override
    public int updateDroneState(String droneId, DroneState droneState) {
        return droneRepository.updateDroneState(droneId, droneState);
    }
}
