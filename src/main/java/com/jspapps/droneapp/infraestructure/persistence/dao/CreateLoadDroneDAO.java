package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.domain.dto.RegisterLoadDrone;
import com.jspapps.droneapp.domain.port.CreateLoadDronePort;
import com.jspapps.droneapp.domain.port.ListDronePort;
import com.jspapps.droneapp.infraestructure.persistence.DroneLoadRepository;
import com.jspapps.droneapp.infraestructure.persistence.model.DroneLoad;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.text.MessageFormat;
import java.util.List;

@AllArgsConstructor
@PersistenceAdapter
public class CreateLoadDroneDAO implements CreateLoadDronePort {

    private final ModelMapper modelMapper;
    private final ListDronePort listDronePort;
    private final DroneLoadRepository droneLoadRepository;

    @Override
    public RegisterLoadDrone registerLoad(RegisterLoadDrone droneLoad) {
        var newLoad = modelMapper.map(droneLoad, DroneLoad.class);
        newLoad = droneLoadRepository.saveAndFlush(newLoad);
        return modelMapper.map(newLoad, RegisterLoadDrone.class);
    }

    private boolean isEnableToLoad(DroneState droneState) {
        return List.of(DroneState.IDLE, DroneState.LOADING).contains(droneState);
    }

    @Override
    public void checkDroneToRegisterLoad(String droneId) {
        var drone = listDronePort.findDroneById(droneId);
        if (drone == null) {
            throw new RuntimeException("Drone not found.");
        }

        var isDroneEnabled = isEnableToLoad(drone.getState());
        if (!isDroneEnabled) {
            throw new RuntimeException(MessageFormat.format("Drone is disable to load medications, status is {0}", drone.getState().toString()));
        }
    }
}
