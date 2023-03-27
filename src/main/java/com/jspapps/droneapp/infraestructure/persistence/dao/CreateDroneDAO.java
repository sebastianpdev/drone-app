package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.RandomUUIDGenerator;
import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.domain.dto.CreateDrone;
import com.jspapps.droneapp.domain.port.CreateDronePort;
import com.jspapps.droneapp.infraestructure.persistence.DroneRepository;
import com.jspapps.droneapp.infraestructure.persistence.model.Drone;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@PersistenceAdapter
public class CreateDroneDAO implements CreateDronePort {

    private final ModelMapper modelMapper;

    private final DroneRepository droneRepository;

    @Override
    public CreateDrone save(CreateDrone createDrone) {
        var droneStored = droneRepository.findDroneBySerial(createDrone.getSerial()).orElse(null);
        if (droneStored != null) {
            throw new RuntimeException("Drone already exists by serial.");
        }

        var newDrone = modelMapper.map(createDrone, Drone.class);
        newDrone.setId(RandomUUIDGenerator.generateRandomUUIDString());
        newDrone.setState(DroneState.IDLE);
        newDrone = droneRepository.saveAndFlush(newDrone);
        return modelMapper.map(newDrone, CreateDrone.class);
    }
}
